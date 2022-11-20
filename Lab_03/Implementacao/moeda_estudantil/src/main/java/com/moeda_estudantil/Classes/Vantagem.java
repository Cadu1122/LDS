package com.moeda_estudantil.Classes;

import java.io.Serializable;

public class Vantagem implements Serializable {

    private Integer id;

    private String nome;

    private String descricao;

    private Integer custo;

    private Empresa empresa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getCusto() {
        return custo;
    }

    public void setCusto(Integer custo) {
        this.custo = custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void alterar(String nome, String descricao, Integer custo, Empresa empresa) {
        setCusto(custo);
        setDescricao(descricao);
        setEmpresa(empresa);
        setNome(nome);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vantagem)) {
            return false;
        }
        Vantagem vantagem = (Vantagem) obj;
        return vantagem.getNome().equals(this.getNome());
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
