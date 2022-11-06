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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.moeda_estudantil.Classes.Aluno;
import com.moeda_estudantil.Classes.Historico;
import com.moeda_estudantil.Classes.Professor;

public class HistoricoDAO {

    private static final File ARQUIVO_ARMAZENAMENTO = new File("Historico.dat");

    private static Set<Historico> historicos = new LinkedHashSet<Historico>();

    private static final HistoricoDAO HISTORICO_DAO = new HistoricoDAO();

    public static final HistoricoDAO getInstance() {
        return HISTORICO_DAO;
    }

    public List<Historico> encontrar(Professor professor) {
        recuperar();
        return historicos.stream()
               .filter(h -> h.getProfessor().equals(professor))
               .toList();
    }

    public List<Historico> encontrar(Aluno aluno) {
        recuperar();
        return historicos.stream()
               .filter(h -> h.getAluno().equals(aluno))
               .toList();
    }

    private HistoricoDAO() {
        recuperar();
    }

    public Set<Historico> getHistoricos() {
        return historicos;
    }

	/**
	 * Carrega os empresas do banco de dados
	 */
	@SuppressWarnings("unchecked")
	private static void recuperar() {
		try {
			if (ARQUIVO_ARMAZENAMENTO.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
				historicos = (Set<Historico>) objectInputStream.readObject();
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

    public void salvar(Historico historico) {
        historicos.add(historico);
        armazenarAlunos();
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
			objectOutputStream.writeObject(historicos);
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
