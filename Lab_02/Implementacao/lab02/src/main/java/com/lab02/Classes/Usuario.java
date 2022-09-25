package com.lab02.Classes;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Usuario implements Serializable {
    private Integer id;

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String endereco;

    @NotBlank
    @NotNull
    private String senha;

    public Usuario(Integer id, String nome, String endereco, String senha) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.senha = senha;
    }

    public Usuario() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
