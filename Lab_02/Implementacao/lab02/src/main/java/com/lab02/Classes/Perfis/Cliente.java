package com.lab02.Classes.Perfis;

import java.util.Map;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Cliente extends Usuario {
    public static final int MAX_ENTIDADES = 3;

    @NotBlank
    @NotNull
    private String cpf;

    @NotBlank
    @NotNull
    private String rg;

    @NotBlank
    @NotNull
    private String profissao;

    private Map<String, Double> entidadesEmpregadoras;

    @NotNull
    @DecimalMin(value = "0", inclusive = true)
    private Double credito;

    public Cliente(Integer id, String nome, String endereco, String senha, String cpf, String rg, String profissao,
            Map<String, Double> entidadesEmpregadoras, Double credito) {
        super(id, nome, endereco, senha);
        this.cpf = cpf;
        this.rg = rg;
        this.profissao = profissao;
        this.entidadesEmpregadoras = entidadesEmpregadoras;
        this.credito = credito;
    }

    public Cliente() {
        
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

    public Map<String, Double> getEntidadesEmpregadoras() {
        return entidadesEmpregadoras;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        if(credito < 0) {
            throw new IllegalStateException("A quantidade de créditos não pode ser menor que 0");
        }
        this.credito = credito;
    }

    public void adicionarCredito(int valor) {
        this.credito += credito;
    }

    @Override
    public String toString() {
        return "Cliente: " + this.getId();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cliente) {
            Cliente cliente = (Cliente) obj;
            return getId().equals(cliente.getId());
        }
        return false;
    }
}
