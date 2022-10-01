package com.lab02.Classes.GerenciamentoDeAlugueis;

import com.lab02.Classes.Perfis.Cliente;

public class Pedido {
    private Cliente locatario;
    private Automovel automovel;
    private int avaliacao;
    private Estado estado;

    public Pedido(Cliente locatario, Automovel automovel) {
        this.locatario = locatario;
        this.automovel = automovel;
        this.estado = Estado.EM_CONSIDERACAO;
    }

    public Cliente getLocatario() {
        return locatario;
    }

    public void setLocatario(Cliente locatario) {
        this.locatario = locatario;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String consultar() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "automóvel: " + this.automovel + ", locatário: " + this.locatario +
        ", estado: " + this.estado + ", avaliação: " + this.avaliacao;
    }

}
