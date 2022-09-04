package business;

import java.util.Set;
import java.util.TreeSet;

public class Turma {
	private boolean ativo;
	private static final int MIN_ALUNOS = 3;
	private static final int MAX_ALUNOS = 60;
	private String nome;
	private Set<Curso> cursos;
	private boolean obrigatorio;
	private double valorMensalidade;
	private int qtdAlunos;

	public boolean isAtivo() {
		return ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public static int getMinAlunos() {
		return MIN_ALUNOS;
	}

	public static int getMaxAlunos() {
		return MAX_ALUNOS;
	}

	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setValorMensalidade(double valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}

	public double getValorMensalidade() {
		return valorMensalidade;
	}

	public void addCurso(Curso curso) {
		this.cursos.add(curso);
	}

	public int getQtdAlunos() {
		return qtdAlunos;
	}

	public Turma(String nome, Curso curso, boolean obrigatorio) {
		this.cursos = new TreeSet<Curso>();
		this.ativo = false;
		this.nome = nome;
		this.addCurso(curso);
		this.obrigatorio = obrigatorio;
	}

	private boolean verificaQtdAluno() {
		return this.qtdAlunos >= MIN_ALUNOS;
	}

	public void adicionarAluno(Aluno aluno) {
		if(this.qtdAlunos >= 60) {
			throw new IllegalStateException("Turma está com número máximo de alunos");
		}
		if(aluno.equals(null)) {
			throw new IllegalStateException("Aluno não pode ser nulo");
		}
		this.qtdAlunos++;
		if(verificaQtdAluno()) {
			this.ativo = true;
		}
	}

	public void retirarAluno(Aluno aluno) {
		if(qtdAlunos >= 0) {
			throw new IllegalStateException("Não existem alunos nessa turma");
		}
		if(aluno.equals(null)) {
			throw new IllegalStateException("Aluno não pode ser nulo");
		}
		this.qtdAlunos--;
		if(verificaQtdAluno()) {
			this.ativo = false;
		}
	}
}
