package business;

import java.util.Set;

public class Turma {
	private boolean ativo;
	private double valor;
	private static final int MIN_ALUNOS = 3;
	private static final int MAX_ALUNOS = 60;
	private String nome;
	private Set<Curso> cursos;
	private boolean obrigatorio;
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

	public double getValor() {
		return valor;
	}

	public void setValor(int valor) {
		if (valor >= 0) {
			this.valor = valor;
		}
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

	public void addCurso(Curso curso) {
		this.cursos.add(curso);
	}

	public int getQtdAlunos() {
		return qtdAlunos;
	}

	public Turma(int valor, String nome, Curso curso, boolean obrigatorio) {
		this.ativo = false;
		this.valor = valor;
		this.nome = nome;
		this.addCurso(curso);
		this.obrigatorio = obrigatorio;
	}

	private boolean verificaQtdAluno() {
		return this.qtdAlunos >= MIN_ALUNOS;
	}

	public void adicionaAluno(Aluno aluno) {
		if(aluno.equals(null)) {
			throw new IllegalStateException("Aluno não pode ser nulo");
		}
		this.qtdAlunos++;
		if(verificaQtdAluno()) {
			this.ativo = true;
		}
	}

	public void retirarAluno(Aluno aluno) {
		if(aluno.equals(null)) {
			throw new IllegalStateException("Aluno não pode ser nulo");
		}
		this.qtdAlunos--;
		if(verificaQtdAluno()) {
			this.ativo = false;
		}
	}
}
