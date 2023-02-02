package com.example.authblock;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;

import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivateKey;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "")
public class SiteController {
    @GetMapping("")
    public String home(){
        return "index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
    @GetMapping("/registrazione")
    public String registrazione(){
        return "registrazione";
    }

    /*@GetMapping("/download")
    public String download(){
        return "download";
    }*/


    @PostMapping("/download")
    public String download(@RequestParam("addressEther") String addressEth, HttpServletRequest request, Model model) throws Exception {
        FileWriter file;
        Utils indirizzo = new Utils();
        // verifico se l'indirizzo è sintatticamente valido
        if(indirizzo.isAddress(addressEth)){
            //genero la chiave
            String key = indirizzo.generatekey();
            // creo un oggetto json nel quale salvare la coppia indirizzo:chiave
            JSONObject obj = new JSONObject();
            obj.put(addressEth, key);
            try {
                // creo il file per salvare all'interno gli oggetti json
                file = new FileWriter("src/main/java/com/example/authblock/offBlockchain.txt");
                file.write(obj.toString());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "download";
        }else{
            System.out.println("codice non valido");
            model.addAttribute("flag",true);
            return "contact";

        }



    }

    }
