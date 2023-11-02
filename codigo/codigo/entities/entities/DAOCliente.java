package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class DAOCliente implements DAO<Cliente> {

    private String nomeArq;
    private Scanner arqLeitura;
    private FileWriter arqEscrita;

    public DAOCliente(String nomeArq) {
        this.nomeArq = nomeArq;
        this.arqEscrita = null;
        this.arqLeitura = null;
    }

    public void abrirLeitura() throws IOException {
        if (arqEscrita != null) {
            arqEscrita.close();
            arqEscrita = null;
        }
        arqLeitura = new Scanner(new File(nomeArq), Charset.forName("UTF-8"));
    }

    public void abrirEscrita() throws IOException {
        if (arqLeitura != null) {
            arqLeitura.close();
            arqLeitura = null;
        }
        arqEscrita = new FileWriter(nomeArq, Charset.forName("UTF-8"), true);
    }

    public void fechar() throws IOException {
        if (arqEscrita != null)
            arqEscrita.close();
        if (arqLeitura != null)
            arqLeitura.close();
        arqEscrita = null;
        arqLeitura = null;
    }

    public Cliente getNext() {
        String[] linha = arqLeitura.nextLine().split(";");
        String tipo = linha[0].toLowerCase();

        if (tipo.equals("cliente")) {
            return new Cliente(linha[0], linha[1]);
        }

        return null;
    }

    public void add(Cliente dado) throws IOException {
        arqEscrita.append(dado.dataToText() + "\n");
    }

    public Cliente[] getAll() {
        int TAM_MAX = 10000;
        int cont = 0;
        Cliente[] dados = new Cliente[TAM_MAX];
        try {
            fechar();
            abrirLeitura();
            while (arqLeitura.hasNext()) {
                dados[cont] = this.getNext();
                cont++;
            }
        } catch (IOException exception) {
            arqEscrita = null;
            arqLeitura = null;
            dados = null;
        }
        dados = Arrays.copyOf(dados, cont);
        return dados;
    }

    public void addAll(Cliente[] dados) {
        try {
            fechar();
            abrirEscrita();
            for (Cliente comida : dados) {
                if (comida != null)
                    add(comida);
            }
        } catch (IOException e) {
            arqEscrita = null;
            arqLeitura = null;
        }
    }
}
