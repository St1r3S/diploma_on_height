package ua.com.diploma.onheight.controller;

import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.diploma.onheight.model.company.Contact;
import ua.com.diploma.onheight.model.user.User;
import ua.com.diploma.onheight.service.ContactService;
import ua.com.diploma.onheight.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping(path = "/contact")
public class ContactController {

    private final ContactService contactService;

    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @ModelAttribute
    public void addAttributes(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("companyId", user.getCompany().getId());
    }

    @GetMapping("/create")
    public String userContactForm(Contact contact) {
        return "contact/create-contact";
    }

    @GetMapping("/list")
    public String contactsList(@RequestParam(required = false) String searchParam,
                               @RequestParam(required = false) String searchValue,
                               Authentication authentication,
                               Model model) {
        User user = userService.findByUsername(authentication.getName());
        if (Objects.equals(searchParam, "lastName") && searchValue != null) {
            model.addAttribute("contacts",
                    contactService.findAllByCompanyIdAndLastName(user.getCompany().getId(), searchValue));
        } else if (Objects.equals(searchParam, "email") && searchValue != null) {
            model.addAttribute("contacts",
                    contactService.findAllByCompanyIdAndEmail(user.getCompany().getId(), searchValue));
        } else {
            model.addAttribute("contacts",
                    contactService.findAllByCompanyId(user.getCompany().getId()));
        }
        return "contact/contacts-list";
    }


    @PostMapping("/create")
    public String createContact(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors() || !saveContact(contact)) {
            return "contact/create-contact";
        }
        return "redirect:list";
    }


    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id, Model model) {
        Contact contact = contactService.findById(id);

        model.addAttribute("contact", contact);
        return "contact/update-contact";
    }

    @PostMapping("/update/{id}")
    public String updateContact(@PathVariable("id") long id,
                                @Valid Contact contact,
                                BindingResult result) {
        if (result.hasErrors() || !saveContact(contact)) {
            contact.setId(id);
            return "contact/update-contact";
        }
        return "redirect:/contact/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") long id) {

        contactService.deleteById(id);

        return "redirect:/contact/list";
    }

    private boolean saveContact(Contact contact) {
        try {
            contactService.save(contact);
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

}
