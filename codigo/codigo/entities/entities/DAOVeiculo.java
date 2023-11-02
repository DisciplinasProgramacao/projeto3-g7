package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class DAOVeiculo implements DAO<Veiculo> {

    private String nomeArq;
    private Scanner arqLeitura;
    private FileWriter arqEscrita;

    public DAOVeiculo(String nomeArq) {
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

    public Veiculo getNext() {
        String[] linha = arqLeitura.nextLine().split(";");
        String tipo = linha[0].toLowerCase();
        
        return new Veiculo(tipo);

    }

    public void add(Veiculo dado) throws IOException {
        arqEscrita.append(dado.dataToText() + "\n");
    }

    public Veiculo[] getAll() {
        int TAM_MAX = 10000;
        int cont = 0;
        Veiculo[] dados = new Veiculo[TAM_MAX];
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

    public void addAll(Veiculo[] dados) {
        try {
            fechar();
            abrirEscrita();
            for (Veiculo veiculo : dados) {
                if (veiculo != null)
                    add(veiculo);
            }
        } catch (IOException e) {
            arqEscrita = null;
            arqLeitura = null;
        }
    }
}
