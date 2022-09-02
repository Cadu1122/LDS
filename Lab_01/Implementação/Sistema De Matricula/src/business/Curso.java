package business;

public enum Curso {
	ENGENHARIA_DE_SOFTWARE(60),
	CIENCIAS_DA_COMPUTACAO(50),
	ENGENHARIA_CIVIL(45),
	ADMINISTRACAO(50);

	public int numCreditos;

	private Curso(int numCreditos) {
		this.numCreditos = numCreditos;
	}

	@Override
	public String toString() {
		return super.toString().replace("_", " ").toLowerCase();
	}
}
