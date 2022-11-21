package com.moeda_estudantil.Classes;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    
    private String login;

    private String senha;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    public Usuario() {

    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean logar(String login, String senha) {
        return login.equals(login) && senha.equals(senha);
    }
}
