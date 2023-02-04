package com.example.authblock;

import java.util.ArrayList;
import java.util.Arrays;

public class InfoAccessoUtente {
    private String oraLogin, oraLogout, url;
    private InfoAccessoUtente( String url, String oraLogin, String oraLogout){
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
        return new ArrayList<>(Arrays.asList(url, oraLogin, oraLogout));
    }


    public static class InfoAccessoUtenteBuilder {
        private String oraLogin, oraLogout, url;

        public InfoAccessoUtenteBuilder() {
        }

        public InfoAccessoUtenteBuilder setOraLogin(String oraLogin) {
            this.oraLogin = oraLogin;
            return this;
        }

        public InfoAccessoUtenteBuilder setOraLogout(String oraLogout) {
            this.oraLogout = oraLogout;
            return this;
        }

        public InfoAccessoUtenteBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public InfoAccessoUtente build() {
            return new InfoAccessoUtente(url, oraLogin, oraLogout);
        }
    }
}