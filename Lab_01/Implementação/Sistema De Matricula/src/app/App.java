package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import business.Aluno;
import business.MatriculaForaDoPrazo;
import business.MensalidadePaga;
import business.Professor;
import business.Secretario;
import business.Turma;
import business.Usuario;

public class App {
	public static final File ARQUIVO_ARMAZENAMENTO = new File("historico.dat");
	public static Set<Usuario> usuarios = new HashSet<Usuario>();
	public static Set<Turma> turmas = new HashSet<Turma>();
	public static Scanner teclado = new Scanner(System.in);
	public static int idGenerator = 1;

	public static void main(String[] args) {
		recuperarUsuarios();
		inicializaSecretario();
		int num, aux;
		String nome, senha;
		Usuario x=new Aluno("joao","asda");
		usuarios.add(x);
		System.out.println("Digite 1 para cadastrar um usuario ou 0 para sair");
		num = teclado.nextInt();
		do {
			switch (num) {
			case 1:
				System.out.println("Digite o nome para cadastrar o usuario");
				nome = teclado.next();
				System.out.println("Digite a senha para cadastrar o usuario");
				senha = teclado.next();
				System.out.println("Digite 1 para cadastrar o usuario como aluno, 2 para professor e 3 como secretario");
				aux = teclado.nextInt();
				switch (aux) {
				case 1:
					Aluno aluno = new Aluno(nome, senha);
					usuarios.add(aluno);
					System.out.println("digite 0 para sair");
					num = teclado.nextInt();
					break;
				case 2:
					Professor professor = new Professor(nome, senha);
					usuarios.add(professor);
					System.out.println("digite 0 para sair");
					num = teclado.nextInt();
					break;
				case 3:
					Secretario secretario = new Secretario(nome, senha);
					usuarios.add(secretario);
					System.out.println("digite 0 para sair");
					num = teclado.nextInt();
					break;
				default:
					System.out.println("Numero digitado invalido "+num);
				}
			}
		} while (num != 0);
		System.out.println("Digite 1 para realizar login e digite 0 para sair");
		num=teclado.nextInt();
		do
		{
			switch (num)
			{
			case 1:
				System.out.println("Digite o nome");
				nome=teclado.next();
				System.out.println("Digite a senha");
				senha=teclado.next();
				for (Usuario u:usuarios)
				{
					if (u.getNome().equals(nome)&&u.getSenha().equals(senha))
					{
						if (u instanceof Aluno)
						{
							System.out.println("Digite 1 para realizar matricula");
							System.out.println("Digite 2 para realizar calcular mensalidade");
							System.out.println("Digite 3 para pagar a mensalidade");
							num=teclado.nextInt();
							do {
								switch (num)
								{
								case 1:
									try {
										((Aluno) u).matricular();
										System.out.println("digite 0 para sair");
										num = teclado.nextInt();
										break;
									} catch (MatriculaForaDoPrazo e) {
										System.out.println(e.getMessage());
										System.out.println("digite 0 para sair");
										num = teclado.nextInt();
										break;
									}
								case 2:
									((Aluno) u).calcularMensalidade();
									System.out.println("digite 0 para sair");
									num = teclado.nextInt();
									break;
								case 3:
									try {
										((Aluno) u).pagarMensalidade();
										System.out.println("digite 0 para sair");
										num = teclado.nextInt();
										break;
									} catch (MensalidadePaga e) {
										System.out.println(e.getMessage());
										System.out.println("digite 0 para sair");
										num = teclado.nextInt();
										break;
									}
									default:
										System.out.println("numero digitado incorreto "+num);
								}
									
							}while(num!=0);
						}
						else if(u instanceof Professor)
						{
							System.out.println("Digite 1 para listar as turmas do professor");
							do {
								switch(num)
								{
								case 1:
									System.out.println(((Professor) u).listarTurma());
									System.out.println("digite 0 para sair");
									num = teclado.nextInt();
									break;
								default:
									System.out.println("Numero digitado invalido "+num);
								}
							}while(num!=0);
						}
						else if (u instanceof Secretario)
						{
							System.out.println("Digite 1 para setar prazo da matricula");
							System.out.println("Digite 2 para adicionar o aluno");
							System.out.println("Digite 3 para adicionar o professor");
							System.out.println("Digite 4 para adicionar turma");
							System.out.println("Digite 5 para alterar aluno");
							System.out.println("Digite 6 para alterar professor");
							System.out.println("Digite 7 para alterar turma");
							System.out.println("Digite 8 para excluir aluno");
							System.out.println("Digite 9 para excluir professor");
							System.out.println("Digite 10 para excluir turma");
							num = teclado.nextInt();
							do {
								switch (num)
								{
								case 1:
									((Secretario) u).setPrazoMatricula();
								case 2:
									((Secretario) u).adicionarAluno(null, null);
								}
							}
							
						}
					}
				}
			}
		}while(num!=0);
		armazenarUsuarios();
		
	}

	/**
	 * Carrega os usuarios do banco de dados
	 */
	@SuppressWarnings("unchecked")
	public static void recuperarUsuarios() {
		try {
			if (ARQUIVO_ARMAZENAMENTO.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
				usuarios = (Set<Usuario>) objectInputStream.readObject();
				objectInputStream.close();
				idGenerator = usuarios.stream().mapToInt(b -> b.getId()).max().orElse(0) + 1;
			}
		} catch (SecurityException e) {
			System.err.println("O acesso ao arquivo foi negado");
			esperar();
		} catch (OptionalDataException e) {
			System.err.println("Os dados foram armazenados de forma incorreta no ultimo salvamento");
			esperar();
		} catch (StreamCorruptedException e) {
			System.err.println("Ocorreu um erro ao ler os arquivos");
			esperar();
		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao acessar os arquivos");
			esperar();
		} catch (Exception e) {
			System.err.println("Um erro inesperado ocorreu");
			esperar();
		}
	}

	/**
	 * Dá ao usuário tempo para ler a mensagem na tela
	 */
	public static void esperar() {
		System.out.println("---------------------------------------------");
		System.out.println("Pressione enter para continuar");
		teclado.nextLine();
	}

	/**
	 * Salva usuarios no banco de dados
	 */
	public static void armazenarUsuarios() {
		try {
			if (!ARQUIVO_ARMAZENAMENTO.exists()) {
				ARQUIVO_ARMAZENAMENTO.createNewFile();
			}
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ARMAZENAMENTO));
			objectOutputStream.writeObject(usuarios);
			objectOutputStream.close();
		} catch (SecurityException e) {
			System.err.println("O acesso ao arquivo foi negado.");
			esperar();
		} catch (StreamCorruptedException e) {
			System.err.println("Ocorreu um erro ao salvar os arquivos.");
			esperar();
		} catch (NotSerializableException e) {
			System.err.println("Não foi possivel tranferir os dados para o arquivo.");
			esperar();
		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao acessar os arquivos.");
			esperar();
		} catch (Exception e) {
			System.err.println("Um erro inesperado ocorreu.");
			esperar();
		}
	}
	public static void inicializaSecretario()
	{
		if (usuarios.size()==0)
		{
			Secretario secretario=new Secretario("Adao","1234");
			usuarios.add(secretario);
		}
	}
}
