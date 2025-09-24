package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError() {
        // Custom error message
        return "Jenkins Full Practice Successful";
    }

    // For Spring Boot 2.3+, override getErrorPath (optional, deprecated in newer versions)
    public String getErrorPath() {
        return "/error";
    }
}
