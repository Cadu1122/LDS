package com.lab02.DTOS;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lab02.Classes.Cliente;

public class ClienteDTO extends UsuarioDTO {
    @NotBlank
    @NotNull
    private String cpf;

    @NotBlank
    @NotNull
    private String rg;

    @NotBlank
    @NotNull
    private String profissao;

    @NotNull
    @DecimalMin(value = "0", inclusive = true)
    private Double credito;

    public ClienteDTO(String nome, String endereco, String senha, String cpf, String rg, String profissao,
            Double credito) {
        super(nome, endereco, senha);
        this.cpf = cpf;
        this.rg = rg;
        this.profissao = profissao;
        this.credito = credito;
    }

    public ClienteDTO(Cliente cliente) {
        this(cliente.getNome(), cliente.getEndereco(), cliente.getSenha(), cliente.getCpf(), 
        cliente.getRg(), cliente.getProfissao(), cliente.getCredito());
    }

    public ClienteDTO() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setCredito(credito);
        cliente.setEndereco(this.getEndereco());
        cliente.setNome(this.getNome());
        cliente.setProfissao(profissao);
        cliente.setRg(rg);
        cliente.setSenha(this.getSenha());
        return cliente;
    }
}
