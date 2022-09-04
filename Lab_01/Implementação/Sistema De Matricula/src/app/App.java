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
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import business.Usuario;

public class App {
    public static final File ARQUIVO_ARMAZENAMENTO = new File("historico.dat");
    public static Set<Usuario> usuarios = new TreeSet<Usuario>();
    public static Scanner teclado = new Scanner(System.in);
    public static int idGenerator = 1;

    public static void main(String[] args) {
        recuperarUsuarios();
        armazenarUsuarios();
    }

    /**
     * Carrega os usuarios do banco de dados
     */
    @SuppressWarnings("unchecked")
    public static void recuperarUsuarios() {
        try {
            if(ARQUIVO_ARMAZENAMENTO.exists()) {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_ARMAZENAMENTO));
                usuarios = (Set<Usuario>) objectInputStream.readObject();
                objectInputStream.close();
                idGenerator = usuarios.stream()
                    .mapToInt(b -> b.getId())
                    .max()
                    .orElse(0) + 1;
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
            if(!ARQUIVO_ARMAZENAMENTO.exists()) {
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
}
