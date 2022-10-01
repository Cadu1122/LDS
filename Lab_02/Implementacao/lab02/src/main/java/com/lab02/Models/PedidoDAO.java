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

import com.lab02.Classes.GerenciamentoDeAlugueis.Pedido;

public class PedidoDAO {
    private static final File ARQUIVO_ARMAZENAMENTO = new File("Pedidos.dat");
    private Set<Pedido> pedidos;

    public PedidoDAO() {
        pedidos = new HashSet<Pedido>();
        recuperarPedidos();
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void criarPedido(Pedido pedido) {
        pedidos.add(pedido);
        armazenarPedidos();
    }

    /**
	 * Carrega os pedidos do banco de dados
	 */
	@SuppressWarnings("unchecked")
	private void recuperarPedidos() {
		try {
			if (ARQUIVO_ARMAZENAMENTO.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
				pedidos = (Set<Pedido>) objectInputStream.readObject();
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

    /**
	 * Salva pedidos no banco de dados
	 */
	private void armazenarPedidos() {
		try {
			if (!ARQUIVO_ARMAZENAMENTO.exists()) {
				ARQUIVO_ARMAZENAMENTO.createNewFile();
			}
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ARMAZENAMENTO));
			objectOutputStream.writeObject(pedidos);
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
