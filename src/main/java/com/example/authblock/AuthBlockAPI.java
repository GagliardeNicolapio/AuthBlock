package com.example.authblock;

import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api", method = POST)
public class AuthBlockAPI {
    @GetMapping(value = "", produces = "application/json")
    public String insertAccesso(@RequestParam String id){
        System.out.println("*****"+id);
        return "prova";
    }

}
