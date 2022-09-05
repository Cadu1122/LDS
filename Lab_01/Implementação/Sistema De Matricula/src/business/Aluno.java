package business;

import java.io.Serializable;
import java.time.LocalDate;

public class Aluno extends Usuario implements Serializable{
private static int MAX_DOBRIGATORIAS = 4;
private static int MAX_DOPTATIVAS = 2;
private double ValorMensalidade;
private Matricula matricula;
private boolean pagouMes;
private LocalDate dataPagamento;

public Aluno(String nome, String senha) {
	super(nome, senha);
	this.pagouMes=false;
	this.dataPagamento = LocalDate.now();
}

public void matricular() throws MatriculaForaDoPrazo{
	if(LocalDate.now().isAfter(Matricula.getPrazo())) {
		throw new MatriculaForaDoPrazo(Matricula.getPrazo(),true);
	}
	else if(LocalDate.now().isBefore(Matricula.getInicio())){
		throw new MatriculaForaDoPrazo(Matricula.getInicio(),false);		
	}
	else {
		Matricula m = new Matricula();
		this.matricula = m;
	}
}

//adiciona turma/disciplina na matricula do aluno
public void cadastraTurma(Turma t) throws MaxDisciplinas{
	if(this.matricula.getNumDObrigatorias()== MAX_DOBRIGATORIAS) {
		throw new MaxDisciplinas(true,MAX_DOBRIGATORIAS);
	}
	else if(this.matricula.getNumDOptativas()== MAX_DOPTATIVAS) {
		throw new MaxDisciplinas(false,MAX_DOPTATIVAS);
	}
	else {
		this.matricula.addTurma(t);
		setMensalidade();
	}
}


public void setMensalidade() {
	this.ValorMensalidade=calcularMensalidade();
}

//soma o valor das turmas cadastradas
public double calcularMensalidade() {
	if(this.pagouMes==false) {
	return this.matricula.getTurmas().stream().filter(t->t instanceof Turma)
			.mapToDouble(Turma::getValorMensalidade).sum();
	}
	else
		return 0.00;
}

public void cancelarMatricula() {
	this.matricula = null;
}

public void pagarMensalidade() throws MensalidadePaga{
	if(this.pagouMes==false) {
		this.pagouMes=true;
		this.dataPagamento=LocalDate.now();
	}
	else {
		throw new MensalidadePaga(this.dataPagamento);
	}
	
	
}

public double getValorMensalidade() {
	return ValorMensalidade;
}

public Matricula getMatricula() {
	return matricula;
}

public void consultaMensalidade() {
	if(this.dataPagamento.isBefore(LocalDate.now().minusMonths(1))) {
		this.pagouMes=false;
	}
	else
	{
		this.pagouMes=true;
	}
}

}
