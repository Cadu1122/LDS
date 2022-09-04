package business;

public abstract class Usuario {
	private String nome;
	private String senha;
	private int id;
	private static int contador;
	
	public static int getContador() {
		return contador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		id=++contador;
	}

	public boolean validarLogin() {
		return (nome!=null&&senha!=null);
	}
}
