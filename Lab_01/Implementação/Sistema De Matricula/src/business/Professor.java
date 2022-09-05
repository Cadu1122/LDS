package business;

import java.io.Serializable;
import java.util.Set;

public class Professor extends Usuario implements Serializable{
	private Set<Turma> turmas;

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}

	public Professor(String nome, String senha) {
		super(nome, senha);

	}

	public String listarTurma() {
		String listaDeTurma="";
		for (Turma turma:turmas)
		{
			listaDeTurma+=turma.getNome();
		}
		return listaDeTurma;
	}

}
