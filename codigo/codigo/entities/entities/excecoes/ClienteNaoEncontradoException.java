package entities.excecoes;

/**
 * Exceção lançada quando um cliente não é encontrado em operações de busca ou validação.
 * Esta exceção herda de RuntimeException, sendo uma exceção não verificada.
 */
public class ClienteNaoEncontradoException extends RuntimeException {

    /**
     * Construtor que cria uma exceção com a mensagem fornecida.
     *
     * @param message Mensagem detalhando a exceção lançada.
     */
    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}