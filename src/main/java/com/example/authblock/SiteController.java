package com.example.authblock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "", method = GET)
public class SiteController {
    @GetMapping("")
    public String home(){
        return "index";
    }
}
