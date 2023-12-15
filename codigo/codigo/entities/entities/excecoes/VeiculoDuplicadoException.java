package entities.excecoes;

/**
 * Exceção lançada quando é detectada uma tentativa de adicionar um veículo duplicado.
 * Herda de RuntimeException, o que significa que é uma exceção não verificada.
 */
public class VeiculoDuplicadoException extends RuntimeException {

    /**
     * Construtor que cria uma exceção com a mensagem fornecida.
     *
     * @param message Mensagem detalhando a exceção lançada.
     */
    public VeiculoDuplicadoException(String message) {
        super(message);
    }
}
