package com.moeda_estudantil.Classes;

public class Aluno extends PessoaFisica{
    private String email;
    private String rg;  
    private String endereco;
    private String curso;


    public Aluno(String login, String senha, String nome, String cpf, String instituicao, String email, String rg,
            String endereco, String curso) {
        super(login, senha, nome, cpf, instituicao);
        this.email = email;
        this.rg = rg;
        this.endereco = endereco;
        this.curso = curso;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public void alterar(Aluno alunoNovo) {
        this.setLogin(alunoNovo.getLogin());
        this.setSenha(alunoNovo.getSenha());
        this.setEmail(alunoNovo.getEmail());
        this.setRg(alunoNovo.getRg());
        this.setEndereco(alunoNovo.getEndereco());
        this.setInstituicao(alunoNovo.getInstituicao());
        this.setCurso(alunoNovo.getCurso());
        this.setCpf(alunoNovo.getCpf());
        this.setNome(alunoNovo.getNome());
        this.setMoedas(alunoNovo.getMoedas());
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Aluno) {
            Aluno comparar = (Aluno) object;
            return comparar.getLogin().equals(this.getLogin());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    
}
