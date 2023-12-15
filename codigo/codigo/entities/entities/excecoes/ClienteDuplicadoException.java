package entities.excecoes;

/**
 * Exceção lançada quando é detectada uma tentativa de adicionar um cliente duplicado.
 * Esta exceção herda de RuntimeException, sendo uma exceção não verificada.
 */
public class ClienteDuplicadoException extends RuntimeException {

    /**
     * Construtor que cria uma exceção com a mensagem fornecida.
     *
     * @param message Mensagem detalhando a exceção lançada.
     */
    public ClienteDuplicadoException(String message) {
        super(message);
    }
}
