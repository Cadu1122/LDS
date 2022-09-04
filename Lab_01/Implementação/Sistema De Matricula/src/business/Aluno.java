package business;

public class Aluno extends Usuario {
	private double ValorMensalidade;
	private static final int DISCIPLINA_OBRIGATORIAS = 4;
	private Curso curso;
	private static final int DISCIPLINA_OPTATIVA = 2;

	public double getValorMensalidade() {
		return ValorMensalidade;
	}

	public void setValorMensalidade(double valorMensalidade) {
		ValorMensalidade = valorMensalidade;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public static int getDisciplinaObrigatorias() {
		return DISCIPLINA_OBRIGATORIAS;
	}

	public static int getDisciplinaOptativa() {
		return DISCIPLINA_OPTATIVA;
	}

	

	public Aluno(String nome, String senha, double valorMensalidade, Curso curso) {
		super(nome, senha);
		ValorMensalidade = valorMensalidade;
		this.curso = curso;
	}

	public void matricular() {

	}

	public double calcularMensalidade() {
		return 0;
	}

	public void pagarMensalidade() {

	}
}
