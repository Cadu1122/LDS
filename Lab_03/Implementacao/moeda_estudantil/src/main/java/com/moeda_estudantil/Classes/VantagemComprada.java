package com.moeda_estudantil.Classes;

import java.io.Serializable;

public class VantagemComprada implements Serializable {

    private Integer codigo;

    private Aluno aluno;

    private Vantagem vantagem;

    public VantagemComprada(Integer codigo, Aluno aluno, Vantagem vantagem) {
        this.codigo = codigo;
        this.aluno = aluno;
        this.vantagem = vantagem;
    }

    public Vantagem getVantagem() {
        return vantagem;
    }

    public void setVantagem(Vantagem vantagem) {
        this.vantagem = vantagem;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
