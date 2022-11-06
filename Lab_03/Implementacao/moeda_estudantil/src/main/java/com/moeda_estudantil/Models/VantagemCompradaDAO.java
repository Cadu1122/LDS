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
import com.moeda_estudantil.Classes.VantagemComprada;

public class VantagemCompradaDAO {
    
    private static final File ARQUIVO_ARMAZENAMENTO = new File("VantagemComprada.dat");

    private static Set<VantagemComprada> vantagensCompradas = new LinkedHashSet<VantagemComprada>();

    private static final VantagemCompradaDAO VANTAGEM_COMPRADA_DAO = new VantagemCompradaDAO();

    public static final VantagemCompradaDAO getInstance() {
        return VANTAGEM_COMPRADA_DAO;
    }

    public List<VantagemComprada> encontrar(Aluno aluno) {
        recuperar();
        return vantagensCompradas.stream()
               .filter(h -> h.getAluno().equals(aluno))
               .toList();
    }

    private VantagemCompradaDAO() {
        recuperar();
    }

    public Set<VantagemComprada> getVantagensCompradas() {
        return vantagensCompradas;
    }

	/**
	 * Carrega os empresas do banco de dados
	 */
	@SuppressWarnings("unchecked")
	private static void recuperar() {
		try {
			if (ARQUIVO_ARMAZENAMENTO.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
				vantagensCompradas = (Set<VantagemComprada>) objectInputStream.readObject();
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

    public void salvar(VantagemComprada vantagemComprada) {
        vantagensCompradas.add(vantagemComprada);
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
			objectOutputStream.writeObject(vantagensCompradas);
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
