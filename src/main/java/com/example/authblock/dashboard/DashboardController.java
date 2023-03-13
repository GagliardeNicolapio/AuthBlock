package com.example.authblock.dashboard;

import com.example.authblock.InfoAccessoSito;
import com.example.authblock.InfoAccessoUtente;
import com.example.authblock.chain.AuthBlockChain;
import com.example.authblock.chain.UtilsChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/dashboard", method = POST)
public class DashboardController {

    @PostMapping("")
    public String loginDashboard(@RequestParam("addrEth") String addressEthereum, Model model) throws Exception {
        System.out.println(addressEthereum);
        //if(!UtilsChain.isAddress(addressEthereum))
        //    throw new RuntimeException("indirizzo non valido");

        AuthBlockChain authBlockChain = new AuthBlockChain();

        if(!authBlockChain.isPresent(addressEthereum)){
            model.addAttribute("flagIsPresent",false);
            return "index";
        }else{
            //l amministratore del sito X può usare l'account eth del sito X come account personale contemporaneamente
            //un account può essere usato per un sito e poi in modo personale o viceversa
  /*          int numAccessi = authBlockChain.getNumberAccessiSito(addressEthereum);
            model.addAttribute("visiteTotali", numAccessi);
            if(numAccessi>0)
                model.addAttribute("accessiSito",
                    authBlockChain.getInfoAccessoSito(addressEthereum, 0, Math.min(numAccessi, 10)));

            numAccessi = authBlockChain.getNumberAccessiUtente(addressEthereum);
            model.addAttribute("sitiVisitati",numAccessi);
            if(numAccessi>0)
                model.addAttribute("accessiUtente",
                        authBlockChain.getInfoAccessoUtente(addressEthereum, 0, Math.min(numAccessi, 10)));
*/

           int numAccessi = authBlockChain.getNumberAccessiSito(addressEthereum);
            ArrayList<InfoAccessoSito> listaAccessoSito = new ArrayList<>();
            for(int i=0; i<numAccessi; i++)
                listaAccessoSito.add(authBlockChain.getInfoAccessoSito(addressEthereum,i));
            model.addAttribute("accessiSito",listaAccessoSito);
            model.addAttribute("visiteTotali", numAccessi);
            System.out.println(listaAccessoSito);

            numAccessi = authBlockChain.getNumberAccessiUtente(addressEthereum);
            ArrayList<InfoAccessoUtente> listaAccessoUtente = new ArrayList<>();
            for(int i=0; i<numAccessi;i++)
                listaAccessoUtente.add(authBlockChain.getInfoAccessoUtente(addressEthereum,i));
            System.out.println(listaAccessoUtente);
            model.addAttribute("accessiUtente",listaAccessoUtente);
            model.addAttribute("sitiVisitati",numAccessi);


            model.addAttribute("addrEth", UtilsChain.truncateAddress(addressEthereum));
            return "dashboard";
        }
    }
}
