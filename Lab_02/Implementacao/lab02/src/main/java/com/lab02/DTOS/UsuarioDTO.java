package com.lab02.DTOS;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioDTO {
    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String endereco;

    @NotBlank
    @NotNull
    private String senha;

    public UsuarioDTO(String nome, String endereco, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.senha = senha;
    }

    public UsuarioDTO() {
        
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
