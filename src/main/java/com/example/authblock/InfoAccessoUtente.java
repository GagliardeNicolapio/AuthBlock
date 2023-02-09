package com.example.authblock;

import java.util.ArrayList;
import java.util.Arrays;

public class InfoAccessoUtente {
    private String  oraLogin;
    private String oraLogout;

    private String url;
    private InfoAccessoUtente( String url){
        this.url = url;
    }
    private InfoAccessoUtente(String oraLogin, String oraLogout, String url){
        this.oraLogin = oraLogin;
        this.oraLogout = oraLogout;
        this.url = url;
    }

    public String getOraLogin() {
        return oraLogin;
    }

    public void setOraLogin(String oraLogin) {
        this.oraLogin = oraLogin;
    }

    public String getOraLogout() {
        return oraLogout;
    }

    public void setOraLogout(String oraLogout) {
        this.oraLogout = oraLogout;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getData(){
        return new ArrayList<>(Arrays.asList(url));
    }


    public static class InfoAccessoUtenteBuilder {
        private String  url;

        public InfoAccessoUtenteBuilder() {
        }


        public InfoAccessoUtenteBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public InfoAccessoUtente build() {
            return new InfoAccessoUtente(url);
        }
        public InfoAccessoUtente buildWithLoginLogout(String oraLogin, String oraLogout) {
            return new InfoAccessoUtente(oraLogin, oraLogout,url);
        }

    }
}