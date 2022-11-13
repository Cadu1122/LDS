package com.moeda_estudantil.Classes;

public class Professor extends PessoaFisica {
    
    public static final Integer QTD_MOEDAS_DADAS = 1000;

    private String departamento;

    public Professor(String login, String senha, String nome, String cpf, String instituicao, String departamento) {
        super(login, senha, nome, cpf, instituicao);
        this.departamento = departamento;
        this.setMoedas(QTD_MOEDAS_DADAS);
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Professor) {
            Professor comparar = (Professor) object;
            return comparar.getLogin().equals(this.getLogin());
        }
        return false;
    }

     public boolean darMoedas(Aluno aluno, int qte){
        if(this.getMoedas()<qte){
            return false;
        }
        else{
            this.setMoedas(this.getMoedas()-qte);
            aluno.setMoedas(aluno.getMoedas()+qte);
            return true;
        }      
    }
}
