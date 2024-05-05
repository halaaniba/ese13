package com.example.ese13.controllers;

import com.example.ese13.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/month")
public class MonthController {

    @GetMapping("")
    public Month getMonth(HttpServletRequest request){
        Month month = (Month) request.getAttribute("MonthInterceptor-month");
        return month;
    }
}

