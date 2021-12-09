package com.project.zzbarber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
public class ErrorHomeController {
    @GetMapping
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("error_message", (Error) request.getSession().getAttribute("errorMessage"));

        return "index";
    }

}
