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

        // verifico se l'indirizzo è sintatticamente valido
        if(UtilsChain.isAddress(addressEth)){
            //genero la chiave
            String key = UtilsCrypto.generatekey();
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
