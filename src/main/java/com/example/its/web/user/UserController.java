package com.example.its.web.user;

import com.example.its.domain.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showlist(Model model){
        var X = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userList", userService.findAll());
        return "users/list";
    }

    @GetMapping("/creationForm")
    public String showCreattionForm(@ModelAttribute UserForm userForm) {
        return "users/creationForm";
    }

    @PostMapping
    public String create(@Validated UserForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return showCreattionForm(form);
        }

        userService.create(form.getUsername(), form.getPassword(), form.getAuthority());
        return "redirect:/users";
    }
}
