package com.moeda_estudantil.Classes;

import java.io.Serializable;

public class Historico implements Serializable {

    private Professor professor;

    private Aluno aluno;

    private Integer quantidade;

    private String motivo;

    public Historico(Professor professor, Aluno aluno, Integer quantidade, String motivo) {
        this.professor = professor;
        this.aluno = aluno;
        this.quantidade = quantidade;
        this.motivo = motivo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
