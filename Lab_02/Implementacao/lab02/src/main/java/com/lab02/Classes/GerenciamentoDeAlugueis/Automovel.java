package com.lab02.Classes.GerenciamentoDeAlugueis;

import com.lab02.Classes.Perfis.Agente;
import com.lab02.Classes.Perfis.Usuario;

public class Automovel {
    private Integer matricula;
    private Integer ano;
    private Usuario dono;
    private Agente locador;
    private String marca;
    private String placa;
    private String modelo;
    private boolean alugado;
    
    public Automovel(Integer matricula, Integer ano, Usuario dono, Agente locador, String marca, String placa,
            String modelo) {
        this.matricula = matricula;
        this.ano = ano;
        this.dono = dono;
        this.locador = locador;
        this.marca = marca;
        this.placa = placa;
        this.modelo = modelo;
        this.alugado = false;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Agente getLocador() {
        return locador;
    }

    public void setLocador(Agente locador) {
        this.locador = locador;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " " + placa;
    }
}
