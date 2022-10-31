package com.moeda_estudantil.Classes;

public class Aluno extends Usuario{
    private String email;
    private String rg;  
    private String endereco;
    private String instituicao;
    private String curso;


    public Aluno(String login, String senha, String email) {
        super(login, senha);
        this.email = email;
    }

    public Aluno(){
        
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
}
