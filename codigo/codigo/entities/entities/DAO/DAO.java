package entities.DAO;

import java.io.IOException;

import entities.IDataToText;

/**
 * Interface que define as operações básicas de um DAO (Data Access Object).
 * Um DAO é responsável por fornecer métodos para acessar e manipular dados de um determinado tipo.
 * @param <T> O tipo de dado que o DAO manipula, que deve implementar a interface IDataToText.
 */
public interface DAO<T extends IDataToText> {

    /**
     * Obtém o próximo dado do DAO.
     * @return O próximo dado do DAO.
     */
    public T getNext();

    /**
     * Obtém todos os dados do DAO.
     * @return Um array contendo todos os dados do DAO.
     * @throws IOException Se ocorrer um erro de leitura dos dados.
     */
    public T[] getAll() throws IOException;

    /**
     * Adiciona um dado ao DAO.
     * @param dado O dado a ser adicionado.
     * @throws IOException Se ocorrer um erro ao escrever o dado.
     */
    public void add(T dado) throws IOException;

    /**
     * Adiciona vários dados ao DAO.
     * @param dados Os dados a serem adicionados.
     */
    public void addAll(T[] dados);
}