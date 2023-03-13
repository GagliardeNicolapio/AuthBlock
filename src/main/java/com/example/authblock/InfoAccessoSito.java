package com.example.authblock;

import java.util.ArrayList;
import java.util.Arrays;

public class InfoAccessoSito {
    private String oraLogin, oraLogout, usernameUtente, userAgentUtente, indirizzoIPUtente;

    @Override
    public String toString() {
        return "InfoAccessoSito{" +
                "oraLogin='" + oraLogin + '\'' +
                ", oraLogout='" + oraLogout + '\'' +
                ", usernameUtente='" + usernameUtente + '\'' +
                ", userAgentUtente='" + userAgentUtente + '\'' +
                ", indirizzoIPUtente='" + indirizzoIPUtente + '\'' +
                '}';
    }

    private InfoAccessoSito(String oraLogin, String oraLogout, String usernameUtente, String userAgentUtente, String indirizzoIPUtente) {
        this.oraLogin = oraLogin;
        this.oraLogout = oraLogout;
        this.usernameUtente = usernameUtente;
        this.userAgentUtente = userAgentUtente;
        this.indirizzoIPUtente = indirizzoIPUtente;
    }

    private InfoAccessoSito(String usernameUtente, String userAgentUtente, String indirizzoIPUtente) {
        this.usernameUtente = usernameUtente;
        this.userAgentUtente = userAgentUtente;
        this.indirizzoIPUtente = indirizzoIPUtente;
    }

    public String getOraLogin(){return oraLogin;}
    public String getOraLogout(){return oraLogout;}


    public String getUsernameUtente() {
        return usernameUtente;
    }

    public void setUsernameUtente(String usernameUtente) {
        this.usernameUtente = usernameUtente;
    }

    public String getUserAgentUtente() {
        return userAgentUtente;
    }

    public void setUserAgentUtente(String userAgentUtente) {
        this.userAgentUtente = userAgentUtente;
    }

    public String getIndirizzoIPUtente() {
        return indirizzoIPUtente;
    }

    public void setIndirizzoIPUtente(String indirizzoIPUtente) {
        this.indirizzoIPUtente = indirizzoIPUtente;
    }

    public ArrayList<String> getData(){
        System.out.println(usernameUtente+" "+ userAgentUtente+" "+ indirizzoIPUtente);
        return new ArrayList<>(Arrays.asList(usernameUtente, userAgentUtente, indirizzoIPUtente));
    }


    public static class InfoAccessoSitoBuilder{
        private String  usernameUtente, userAgentUtente, indirizzoIPUtente;
        public InfoAccessoSitoBuilder(){}

        public InfoAccessoSitoBuilder setUsernameUtente(String username){
            System.out.println("set: "+username);
            this.usernameUtente = username;
            return this;
        }
        public InfoAccessoSitoBuilder setUserAgent(String userAgent){
            this.userAgentUtente = userAgent;
            return this;
        }
        public InfoAccessoSitoBuilder setIpAddress(String ip){
            this.indirizzoIPUtente = ip;
            return this;
        }
        public InfoAccessoSito build(){
            return new InfoAccessoSito(usernameUtente==null ? "" : usernameUtente,userAgentUtente,indirizzoIPUtente);
        }

        public InfoAccessoSito buildWithLoginLogout(String oraLogin, String oraLogout){
            return new InfoAccessoSito(oraLogin, oraLogout, usernameUtente,userAgentUtente,indirizzoIPUtente);
        }
    }


}
