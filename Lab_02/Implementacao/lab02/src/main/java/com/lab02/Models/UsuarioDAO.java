package com.lab02.Models;

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

import com.lab02.Classes.Perfis.Usuario;
import com.lab02.Classes.Perfis.Cliente;

public class UsuarioDAO {
    private static final File ARQUIVO_ARMAZENAMENTO = new File("Usuarios.dat");
    private Set<Usuario> usuarios;
    public static int idGenerator = 1;

    public UsuarioDAO() {
        usuarios = new HashSet<Usuario>();
        recuperarUsuarios();
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

	/**
	 * Carrega os usuarios do banco de dados
	 */
	@SuppressWarnings("unchecked")
	private void recuperarUsuarios() {
		try {
			if (ARQUIVO_ARMAZENAMENTO.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
				usuarios = (Set<Usuario>) objectInputStream.readObject();
				objectInputStream.close();
				idGenerator = usuarios.stream().mapToInt(b -> b.getId()).max().orElse(0) + 1;
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

    public void criarUsuario(Usuario usuario) {
        usuario.setId(idGenerator++);
        usuarios.add(usuario);
        armazenarUsuarios();
    }

    public Usuario encontrarUsuario(Integer id) {
        return usuarios.stream()
               .filter(u -> u.getId().equals(id))
               .findFirst()
               .orElse(null);
    }

    public boolean isLoginValido(Integer id, String senha) {
        Usuario usuario = usuarios.stream()
                            .filter(u -> id.equals(u.getId()))
                            .findFirst()
                            .orElse(null);
        if(usuario == null) {
            return false;
        }
        return usuario.login(id, senha);
    }

    public boolean alterarCliente(Integer id, Cliente cliente) {
        Cliente clienteAntigo = (Cliente) encontrarUsuario(id);
        if(clienteAntigo == null) {
            return false;
        } else {
            clienteAntigo.setCpf(cliente.getCpf());
            clienteAntigo.setCredito(cliente.getCredito());
            clienteAntigo.setEndereco(cliente.getEndereco());
            clienteAntigo.setNome(cliente.getNome());
            clienteAntigo.setProfissao(cliente.getProfissao());
            clienteAntigo.setRg(cliente.getRg());
            clienteAntigo.setSenha(cliente.getSenha());
            armazenarUsuarios();
            return true;
        }
    }

    public boolean excluirUsuario(Integer id) {
        Usuario usuarioAntigo = encontrarUsuario(id);
        if(usuarioAntigo == null) {
            return false;
        } else {
            usuarios.remove(usuarioAntigo);
            armazenarUsuarios();
            return true;
        }
    }

    /**
	 * Salva usuarios no banco de dados
	 */
	private void armazenarUsuarios() {
		try {
			if (!ARQUIVO_ARMAZENAMENTO.exists()) {
				ARQUIVO_ARMAZENAMENTO.createNewFile();
			}
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ARMAZENAMENTO));
			objectOutputStream.writeObject(usuarios);
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
