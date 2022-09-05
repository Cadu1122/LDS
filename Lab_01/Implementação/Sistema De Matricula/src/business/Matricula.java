package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Matricula implements Serializable{
	//atributos:
	
	private static LocalDate prazo;
	private static LocalDate inicio;
	private Set<Turma> turmas;
	
	
	
	
		
	public Matricula() {
		super();
		Set<Turma> aux = new HashSet<Turma>();
		this.turmas = aux;
	}

	public void addTurma(Turma t) {
		this.turmas.add(t);
	}
	
	public void removeTurma(Turma t) {
		this.turmas.remove(t);
	}
	
	public static LocalDate getPrazo() {
		return prazo;
	}
			
	public static LocalDate getInicio() {
		return inicio;
	}

	public static void setPrazo(LocalDate data) {
		Matricula.prazo = data;
	}
	
	public static void setInicio(LocalDate data) {
		Matricula.inicio = data;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	//metodos para retornar o numero de disciplinas ja cadastradas 
	public int getNumDObrigatorias() {
		return this.turmas.stream().filter(t->t.isObrigatorio()==true).collect(Collectors.toList()).size();
	}
	
	public int getNumDOptativas() {
		return this.turmas.stream().filter(t->t.isObrigatorio()==false).collect(Collectors.toList()).size();
	}
	
}
