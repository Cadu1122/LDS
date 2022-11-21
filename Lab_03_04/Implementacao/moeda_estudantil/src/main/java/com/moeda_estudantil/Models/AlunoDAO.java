package com.moeda_estudantil.Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.HashSet;
import java.util.Set;

import com.moeda_estudantil.Classes.Aluno;

public class AlunoDAO {

    public static final AlunoDAO getInstance() {
        return ALUNO_DAO;
    }

    private static final File ARQUIVO_ARMAZENAMENTO = new File("Alunos.dat");
    private static Set<Aluno> alunos = new HashSet<Aluno>();

    private static final AlunoDAO ALUNO_DAO = new AlunoDAO();

    public Aluno encontrarAluno(String login) {
        recuperarAlunos();
        return alunos.stream()
               .filter(u -> u.getLogin().equals(login))
               .findFirst()
               .orElse(null);
    }

    private AlunoDAO() {
        alunos = new HashSet<Aluno>();
        recuperarAlunos();
    }

    public Set<Aluno> getAlunos() {
        recuperarAlunos();
        return alunos;
    }

	/**
	 * Carrega os empresas do banco de dados
	 */
	@SuppressWarnings("unchecked")
	private static void recuperarAlunos() {
		try {
			if (ARQUIVO_ARMAZENAMENTO.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
				alunos = (Set<Aluno>) objectInputStream.readObject();
				objectInputStream.close();
			}
		} catch (SecurityException e) {
			System.err.println("O acesso ao arquivo foi negado");
		} catch (OptionalDataException e) {
			System.err.println("Os dados foram armazenados de forma incorreta no ultimo salvamento");
		} catch (StreamCorruptedException e) {
			System.err.println("Ocorreu um erro ao ler os arquivos");
		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao acessar os arquivos");
		} catch (Exception e) {
			System.err.println("Um erro inesperado ocorreu");
		}
	}

    public void criarAluno(Aluno aluno) {
        alunos.add(aluno);
        armazenarAlunos();
    }

    public boolean alterarAluno(String login, Aluno aluno) {
        Aluno alunoAntigo = (Aluno) encontrarAluno(login);
        if(alunoAntigo == null) {
            return false;
        } else {
            alunoAntigo.alterar(aluno);
            armazenarAlunos();
            return true;
        }
    }

    public boolean excluirAluno(String login) {
        Aluno alunoAntigo = encontrarAluno(login);
        if(alunoAntigo == null) {
            return false;
        } else {
            alunos.remove(alunoAntigo);
            armazenarAlunos();
            return true;
        }
    }

    /**
	 * Salva empresas no banco de dados
	 */
	private void armazenarAlunos() {
		try {
			if (!ARQUIVO_ARMAZENAMENTO.exists()) {
				ARQUIVO_ARMAZENAMENTO.createNewFile();
			}
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ARMAZENAMENTO));
			objectOutputStream.writeObject(alunos);
			objectOutputStream.close();
		} catch (SecurityException e) {
			System.err.println("O acesso ao arquivo foi negado.");
		} catch (StreamCorruptedException e) {
			System.err.println("Ocorreu um erro ao salvar os arquivos.");
		} catch (NotSerializableException e) {
			System.err.println("Não foi possivel tranferir os dados para o arquivo.");
		} catch (IOException e) {
			System.err.println("Ocorreu um erro ao acessar os arquivos.");
		} catch (Exception e) {
			System.err.println("Um erro inesperado ocorreu.");
		}
	}
}
