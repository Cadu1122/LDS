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

import com.moeda_estudantil.Classes.Empresa;

public class EmpresaDAO {
    private static final File ARQUIVO_ARMAZENAMENTO = new File("Empresas.dat");
    private static Set<Empresa> empresas = new HashSet<Empresa>();

    public Empresa encontrarEmpresa(String login) {
        recuperarEmpresas();
        return empresas.stream()
               .filter(u -> u.getLogin().equals(login))
               .findFirst()
               .orElse(null);
    }

    public EmpresaDAO() {
        empresas = new HashSet<Empresa>();
        recuperarEmpresas();
    }

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

	/**
	 * Carrega os empresas do banco de dados
	 */
	@SuppressWarnings("unchecked")
	private static void recuperarEmpresas() {
		try {
			if (ARQUIVO_ARMAZENAMENTO.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
				empresas = (Set<Empresa>) objectInputStream.readObject();
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

    public void criarEmpresa(Empresa empresa) {
        empresas.add(empresa);
        armazenarEmpresas();
    }

    public boolean isLoginValido(String login, String senha) {
        Empresa empresa = empresas.stream()
                            .filter(u -> login.equals(u.getLogin()))
                            .findFirst()
                            .orElse(null);
        if(empresa == null) {
            return false;
        }
        return empresa.logar(login, senha);
    }

    public boolean alterarEmpresa(String login, Empresa empresa) {
        Empresa empresaAntiga = (Empresa) encontrarEmpresa(login);
        if(empresaAntiga == null) {
            return false;
        } else {
            empresaAntiga.setLogin(empresa.getLogin());
            empresaAntiga.setSenha(empresa.getSenha());
            empresaAntiga.setEmail(empresa.getEmail());
            armazenarEmpresas();
            return true;
        }
    }

    public boolean excluirEmpresa(String login) {
        Empresa empresaAntiga = encontrarEmpresa(login);
        if(empresaAntiga == null) {
            return false;
        } else {
            empresas.remove(empresaAntiga);
            armazenarEmpresas();
            return true;
        }
    }

    /**
	 * Salva empresas no banco de dados
	 */
	private void armazenarEmpresas() {
		try {
			if (!ARQUIVO_ARMAZENAMENTO.exists()) {
				ARQUIVO_ARMAZENAMENTO.createNewFile();
			}
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ARMAZENAMENTO));
			objectOutputStream.writeObject(empresas);
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
