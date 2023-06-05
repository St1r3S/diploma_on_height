package ua.com.diploma.onheight.controller;

import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.model.company.Contact;
import ua.com.diploma.onheight.model.manytomany.ProductRequest;
import ua.com.diploma.onheight.model.manytomany.utility.ProductRequestKey;
import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.request.*;
import ua.com.diploma.onheight.model.user.User;
import ua.com.diploma.onheight.model.view.RequestView;
import ua.com.diploma.onheight.service.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
@RequestMapping(path = "/request")
public class RequestController {

    public static final String REQUESTS = "requests";
    private final RequestService requestService;

    private final UserService userService;
    private final CompletionStageService completionStageService;
    private final ProductRequestService productRequestService;
    private final ProductService productService;
    private final CompanyService companyService;
    private final ContactService contactService;

    public RequestController(RequestService requestService, UserService userService, CompletionStageService completionStageService, ProductRequestService productRequestService, ProductService productService, CompanyService companyService, ContactService contactService) {
        this.requestService = requestService;
        this.userService = userService;
        this.completionStageService = completionStageService;
        this.productRequestService = productRequestService;
        this.productService = productService;
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @GetMapping("/add")
    public String guestRequestForm(RequestView requestView, String companyName, Model model) {
        try {
            Company company = companyService.findByCompanyName(companyName);
            model.addAttribute("companyId", company.getId());
            model.addAttribute("products", productService.findAllByCompanyId(company.getId()));
            model.addAttribute("deliveryMethods", Arrays.asList(DeliveryMethod.values()));
            model.addAttribute("paymentMethods", Arrays.asList(PaymentMethod.values()));
            return "request/add-request";
        } catch (EmptyResultDataAccessException ex) {
            return "redirect:/";
        }
    }

    @GetMapping("/create")
    public String userRequestForm(RequestView requestView, Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("companyId", user.getCompany().getId());
        model.addAttribute("products", productService.findAllByCompanyId(user.getCompany().getId()));
        model.addAttribute("deliveryMethods", Arrays.asList(DeliveryMethod.values()));
        model.addAttribute("paymentMethods", Arrays.asList(PaymentMethod.values()));
        return "request/create-request";
    }

    @GetMapping("/list")
    public String requestsList(@RequestParam(required = false) String searchParam,
                               @RequestParam(required = false) String searchValue,
                               Authentication authentication,
                               Model model) {

        User user = userService.findByUsername(authentication.getName());

        if (searchParam != null && searchValue != null) {
            switch (searchParam) {
                case "lastName" ->
                        model.addAttribute(REQUESTS, requestService.findAllByCompanyIdAndLastName(user.getCompany().getId(), searchValue));
                case "email" ->
                        model.addAttribute(REQUESTS, requestService.findAllByCompanyIdAndEmail(user.getCompany().getId(), searchValue));
                case "deliveryMethod" ->
                        model.addAttribute(REQUESTS, requestService.findAllByCompanyIdAndDeliveryMethod(user.getCompany().getId(), DeliveryMethod.getByValue(searchValue)));
                case "requestStatus" ->
                        model.addAttribute(REQUESTS, requestService.findAllByCompanyIdAndCompletionStage_RequestStatus(user.getCompany().getId(), RequestStatus.getByValue(searchValue)));
            }
        } else {
            model.addAttribute(REQUESTS, requestService.findAllByCompanyId(user.getCompany().getId()));
        }
        return "request/requests-list";
    }

    @PostMapping("/add")
    public String addRequest(@Valid RequestView requestView,
                             BindingResult result,
                             Model model,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors() || !saveRequestOnCreate(requestView)) {
            prepareModelIfError(requestView, model);
            return "request/add-request";
        }

        redirectAttributes.addAttribute("userLogin", authentication.getName());
        return "redirect:/";
    }

    @PostMapping("/create")
    public String createRequest(@Valid RequestView requestView, BindingResult result, Model model) {
        if (result.hasErrors() || !saveRequestOnCreate(requestView)) {
            prepareModelIfError(requestView, model);
            return "request/create-request";
        }
        return "redirect:list";
    }


    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id, Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Request request = requestService.findById(id);
        RequestView requestView = RequestView.entityToView(request);

        model.addAttribute("requestView", requestView);
        model.addAttribute("companyId", user.getCompany().getId());
        model.addAttribute("products", productService.findAllByCompanyId(user.getCompany().getId()));
        model.addAttribute("requestStatuses", Arrays.asList(RequestStatus.values()));
        model.addAttribute("deliveryMethods", Arrays.asList(DeliveryMethod.values()));
        model.addAttribute("paymentMethods", Arrays.asList(PaymentMethod.values()));
        return "request/update-request";
    }

    @PostMapping("/update/{id}")
    public String updateRequest(@PathVariable("id") long id, @Valid RequestView requestView, BindingResult result, Model model) {
        if (result.hasErrors() || !saveRequestOnUpdate(requestView)) {
            requestView.setId(id);
            return "request/update-request";
        }
        return "redirect:/request/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRequest(@PathVariable("id") long id, Model model) {

        requestService.deleteById(id);

        return "redirect:/request/list";
    }

    private void prepareModelIfError(RequestView requestView, Model model) {
        model.addAttribute("companyId", requestView.getCompany().getId());
        model.addAttribute("products", productService.findAllByCompanyId(requestView.getCompany().getId()));
        model.addAttribute("deliveryMethods", Arrays.asList(DeliveryMethod.values()));
        model.addAttribute("paymentMethods", Arrays.asList(PaymentMethod.values()));
    }

    private boolean saveRequestOnCreate(RequestView requestView) {
        RequestStatus status = RequestStatus.CREATED;
        Request request = requestService.save(requestView.viewToEntity());
        Product product = productService.findById(requestView.getProductRequest().getProduct().getId());
        int remainingQty = product.getProductQty() - requestView.getProductRequest().getProductQty();
        if (remainingQty >= 0) {
            product.setProductQty(remainingQty);
        } else {
            return false;
        }
        completionStageService.save(new CompletionStage(
                status,
                LocalDateTime.now(),
                request)
        );
        productRequestService.save(new ProductRequest(
                new ProductRequestKey(product.getId(), request.getId()),
                product,
                request,
                requestView.getProductRequest().getProductQty()
        ));
        productService.save(product);
        if (!contactService.existsByEmail(requestView.getEmail())
                && !contactService.existsByPhoneNumber(requestView.getPhoneNumber())) {
            contactService.save(new Contact(
                            requestView.getFirstName(),
                            requestView.getLastName(),
                            requestView.getEmail(),
                            requestView.getPhoneNumber(),
                            "Автоматично створений контакт",
                            requestView.getCompany()
                    )
            );
        }
        return true;
    }

    private boolean saveRequestOnUpdate(RequestView requestView) {
        RequestStatus status = requestView.getCompletionStage().getRequestStatus();
        Request request = requestService.save(requestView.viewToEntity());
        Product product = productService.findById(requestView.getProductRequest().getProduct().getId());
        int remainingQty = product.getProductQty() - requestView.getProductRequest().getProductQty();
        if (remainingQty >= 0) {
            product.setProductQty(remainingQty);
        } else {
            return false;
        }
        CompletionStage completionStage = completionStageService.findByRequestId(request.getId());
        completionStage.setRequestStatus(status);
        completionStage.setCompletionDateTime(LocalDateTime.now());
        completionStageService.save(completionStage);

        ProductRequest productRequest = productRequestService.findAllByRequestId(request.getId()).get(0);
        Product oldProduct = productRequest.getProduct();
        if (!oldProduct.equals(product)) {
            oldProduct.setProductQty(oldProduct.getProductQty() + productRequest.getProductQty());
            product.setProductQty(product.getProductQty() - requestView.getProductRequest().getProductQty());
            productService.save(oldProduct);
        } else {
            product.setProductQty(product.getProductQty() +
                    (productRequest.getProductQty() - requestView.getProductRequest().getProductQty()));
        }
        productService.save(product);
        productRequest.setProductQty(requestView.getProductRequest().getProductQty());
        productRequest.setProduct(product);
        productRequestService.save(productRequest);
        return true;
    }
}
