package com.example.authblock.site;
import com.example.authblock.chain.UtilsChain;
import com.example.authblock.cryptography.UtilsCrypto;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.FileWriter;
import java.io.IOException;


@Controller
@RequestMapping(value = "")
public class SiteController {

    @GetMapping({"testShowData"})
    public String dashBoardLite(){
        return "testShowData";
    }

    @GetMapping({"", "index.html", "index"})
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
    public String download(@RequestParam("addressEther") String addressEth, Model model) throws Exception {

        // verifico se l'indirizzo è sintatticamente valido
        if(/*UtilsChain.isAddress(addressEth)*/true){//metodo provato con indirizzo ethereum account 1 metamask e non funziona
            //genero la chiave
            String key = UtilsCrypto.generatekey();
            // creo un oggetto json nel quale salvare la coppia indirizzo:chiave
            SitoClienteDAO sitoClienteDAO = new SitoClienteDAO();
            sitoClienteDAO.insert(new JSONObject().put(addressEth,key));
            sitoClienteDAO.save();
            model.addAttribute("key", key);
            return "download";
        }else{
            System.out.println("codice non valido");
            model.addAttribute("flag",true);
            return "contact";

        }



    }

    }
