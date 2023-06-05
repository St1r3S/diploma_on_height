package ua.com.diploma.onheight.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.diploma.onheight.model.user.*;
import ua.com.diploma.onheight.service.UserService;

import java.util.Arrays;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute
    public void addAttributes(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("companyId", user.getCompany().getId());
        model.addAttribute("role", UserRole.USER);
        model.addAttribute("genders", Arrays.asList(Gender.values()));
        model.addAttribute("departments", Arrays.asList(Department.values()));
        model.addAttribute("posts", Arrays.asList(Post.values()));
        model.addAttribute("userId", user.getId());
    }

    @GetMapping("/profile")
    public String updateForm(Authentication authentication,
                             Model model) {
        User user = userService.findByUsername(authentication.getName());

        model.addAttribute("user", user);
        return "user/update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @Valid User user,
                             BindingResult result) {
        if (result.hasErrors() || !saveContact(user)) {
            user.setId(id);
            return "user/update-user";
        }
        return "redirect:/user/profile";
    }

    private boolean saveContact(User user) {
        try {
            if (!user.getPasswordHash().equals(userService.findByUsername(user.getUsername()).getPasswordHash())) {
                user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
            }
            userService.save(user);
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}
