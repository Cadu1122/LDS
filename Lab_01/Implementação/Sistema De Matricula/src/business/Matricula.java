import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Matricula {
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

	public static void setPrazo(int dia, int mes, int ano) {
		LocalDate prazo = LocalDate.of(ano, mes, dia);
		Matricula.prazo = prazo;
	}
	
	public static void setInicio(int dia, int mes, int ano) {
		LocalDate ini = LocalDate.of(ano, mes, dia);
		Matricula.inicio = ini;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	//metodos para retornar o numero de disciplinas ja cadastradas 
	public int getNumDObrigatorias() {
		return this.turmas.stream().filter(t->t.isObrigatoria()==true).collect(Collectors.toList()).size();
	}
	
	public int getNumDOptativas() {
		return this.turmas.stream().filter(t->t.isObrigatoria()==false).collect(Collectors.toList()).size();
	}
	
}
