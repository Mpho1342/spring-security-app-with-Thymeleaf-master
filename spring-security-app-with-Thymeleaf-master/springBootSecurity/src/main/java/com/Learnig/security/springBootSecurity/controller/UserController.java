package com.Learnig.security.springBootSecurity.controller;

import com.Learnig.security.springBootSecurity.entities.UserEntity;
import com.Learnig.security.springBootSecurity.entities.student;
import com.Learnig.security.springBootSecurity.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(@ModelAttribute UserEntity user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @GetMapping("/LOGOUT_success")
    public ModelAndView logout(@ModelAttribute UserEntity user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("logout");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserEntity user, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();

        if (bindingResult.hasErrors()) {

            List<String> errorMessage = bindingResult.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            String errorMessageString = errorMessage.toString();
            throw new RuntimeException(errorMessageString);
        }

        userServices.registerUser(user);

        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        mv.addObject("name", name);
        mv.setViewName("home");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
//        mv.addObject("error", error);
        return mv;
    }

//    @PostMapping("/logout")
//    public void logout() {
//
//    }

    @PostMapping("/perform_login")
    public ModelAndView home(@ModelAttribute UserEntity user, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();

        if (bindingResult.hasErrors()) {

            List<String> errorMessage = bindingResult.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            String errorMessageString = errorMessage.toString();
            throw new RuntimeException(errorMessageString);
        }

        userServices.loginUser(user);

        // String name =
        // SecurityContextHolder.getContext().getAuthentication().getName();

        // student st = new student("Murgar", "ADMIN", 12);
        // student st1 = new student("Mega", "USER", 21);
        // student st2 = new student("Mrs Mega", "MANAGER", 30);

        // List<student> stUser = new ArrayList<>();
        // stUser.add(st);
        // stUser.add(st1);
        // stUser.add(st2);

        // mv.addObject("st", st);
        // mv.addObject("studentObj", stUser);
        // mv.addObject("name", name);

        // mv.setViewName("about");
        return mv;
    }
}
