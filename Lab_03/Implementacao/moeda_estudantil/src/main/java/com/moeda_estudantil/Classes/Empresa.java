package com.moeda_estudantil.Classes;

public class Empresa extends Usuario {

    private String email;

    public Empresa(String login, String senha, String email) {
        super(login, senha);
        this.email = email;
    }

    public Empresa() {
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
