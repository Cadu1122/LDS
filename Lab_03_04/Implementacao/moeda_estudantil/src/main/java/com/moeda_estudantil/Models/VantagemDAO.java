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

import com.moeda_estudantil.Classes.Empresa;
import com.moeda_estudantil.Classes.Vantagem;

public class VantagemDAO {

	public static int idGenerator = 1;

    private static final File ARQUIVO_ARMAZENAMENTO = new File("Vantagens.dat");

    private static Set<Vantagem> vantagens = new LinkedHashSet<Vantagem>();

    private static final VantagemDAO VANTAGEM_DAO = new VantagemDAO();

    public static final VantagemDAO getInstance() {
        return VANTAGEM_DAO;
    }

    public List<Vantagem> encontrar(Empresa empresa) {
        recuperar();
        return vantagens.stream()
               .filter(h -> h.getEmpresa().equals(empresa))
               .toList();
    }

	public Vantagem encontrar(Integer id) {
		recuperar();
		return vantagens.stream()
			.filter(v -> v.getId().equals(id))
			.findFirst()
			.orElse(null);
	}

    private VantagemDAO() {
        recuperar();
    }

    public Set<Vantagem> getVantagens() {
        return vantagens;
    }

	/**
	 * Carrega os empresas do banco de dados
	 */
	@SuppressWarnings("unchecked")
	private static void recuperar() {
		try {
			if (ARQUIVO_ARMAZENAMENTO.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
				vantagens = (Set<Vantagem>) objectInputStream.readObject();
				idGenerator = vantagens.stream()
                    .mapToInt(b -> b.getId())
                    .max()
                    .orElse(0) + 1;
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

    public void salvar(Vantagem vantagem) {
		vantagem.setId(idGenerator);
		idGenerator++;
        vantagens.add(vantagem);
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
			objectOutputStream.writeObject(vantagens);
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
