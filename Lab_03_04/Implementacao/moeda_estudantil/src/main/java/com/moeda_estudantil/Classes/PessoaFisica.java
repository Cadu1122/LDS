package com.moeda_estudantil.Classes;

public class PessoaFisica extends Usuario {
    private String nome;
    private String cpf;
    private int moedas;
    private String instituicao;

    public PessoaFisica(String login, String senha, String nome, String cpf, String instituicao) {
        super(login, senha);
        this.nome = nome;
        this.cpf = cpf;
        this.instituicao = instituicao;
    }

    public PessoaFisica() {

    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
