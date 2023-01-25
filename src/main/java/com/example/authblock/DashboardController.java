package com.example.authblock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/dashboard", method = GET)
public class DashboardController {
    @GetMapping("")
    public String home(){
        return "dashboard";
    }
}
