package ua.com.diploma.onheight.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.diploma.onheight.model.user.*;
import ua.com.diploma.onheight.service.CompanyService;
import ua.com.diploma.onheight.service.UserService;

import java.util.Arrays;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final String USER_ROLE = "role";
    public static final String GENDERS = "genders";
    public static final String DEPARTMENTS = "departments";
    public static final String POSTS = "posts";
    public static final String COMPANIES = "companies";

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CompanyService companyService;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder, CompanyService companyService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.companyService = companyService;
    }

    @GetMapping("register")
    public String registrationForm(User user, Model model) {
        model.addAttribute(USER_ROLE, UserRole.USER);
        model.addAttribute(GENDERS, Arrays.asList(Gender.values()));
        model.addAttribute(DEPARTMENTS, Arrays.asList(Department.values()));
        model.addAttribute(POSTS, Arrays.asList(Post.values()));
        model.addAttribute(POSTS, Arrays.asList(Post.values()));
        model.addAttribute(COMPANIES, companyService.findAll());

        return "auth/registration";
    }

    @PostMapping("register")
    public String registerUser(@Valid User user, BindingResult result, Model model) {
        boolean isPresent;
        try {
            userService.findByUsername(user.getUsername());
            isPresent = true;
        } catch (EmptyResultDataAccessException ex) {
            isPresent = false;
        }
        if (isPresent || result.hasErrors()) {
            model.addAttribute(USER_ROLE, UserRole.USER);
            model.addAttribute(GENDERS, Arrays.asList(Gender.values()));
            model.addAttribute(DEPARTMENTS, Arrays.asList(Department.values()));
            model.addAttribute(POSTS, Arrays.asList(Post.values()));

            return "auth/registration";
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userService.save(user);

        return "redirect:login";
    }


    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "redirect:/";
    }

    @GetMapping("/forbidden")
    public String getForbiddenPage() {
        return "auth/forbidden";
    }
}
