package ua.com.diploma.onheight.controller;

import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.diploma.onheight.model.product.PriceCurrency;
import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.product.ProductType;
import ua.com.diploma.onheight.model.user.User;
import ua.com.diploma.onheight.service.ProductService;
import ua.com.diploma.onheight.service.UserService;

import java.util.Arrays;
import java.util.Objects;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @ModelAttribute
    public void addAttributes(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("companyId", user.getCompany().getId());
        model.addAttribute("currencies", Arrays.asList(PriceCurrency.values()));
        model.addAttribute("types", Arrays.asList(ProductType.values()));
    }

    @GetMapping("/create")
    public String userProductForm(@ModelAttribute("warehouseId") long warehouseId,
                                  Product product) {

        return "product/create-product";
    }

    @GetMapping("/list")
    public String productsList(@ModelAttribute("warehouseId") long warehouseId,
                               @RequestParam(required = false) String searchParam,
                               @RequestParam(required = false) String searchValue,
                               Model model) {
        if (Objects.equals(searchParam, "type") && searchValue != null) {
            model.addAttribute("products",
                    productService.findAllByWarehouseIdAndProductType(warehouseId, ProductType.getByValue(searchValue)));
        } else if (Objects.equals(searchParam, "name") && searchValue != null) {
            model.addAttribute("products",
                    productService.findAllByWarehouseIdAndProductName(warehouseId, searchValue));
        } else {
            model.addAttribute("products",
                    productService.findAllByWarehouseId(warehouseId));
        }
        return "product/products-list";
    }


    @PostMapping("/create")
    public String createProduct(@ModelAttribute("warehouseId") long warehouseId,
                                @Valid Product product,
                                BindingResult result) {
        if (result.hasErrors() || !saveProduct(product)) {
            return "product/create-product";
        }
        return "redirect:list";
    }


    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id,
                             @ModelAttribute("warehouseId") long warehouseId,
                             Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/update-product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id,
                                @ModelAttribute("warehouseId") long warehouseId,
                                @Valid Product product,
                                BindingResult result) {
        if (result.hasErrors() || !saveProduct(product)) {
            product.setId(id);
            return "product/update-product";
        }
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id,
                                @ModelAttribute("warehouseId") long warehouseId) {
        productService.deleteById(id);
        return "redirect:/product/list";
    }

    private boolean saveProduct(Product product) {
        try {
            productService.save(product);
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

}
