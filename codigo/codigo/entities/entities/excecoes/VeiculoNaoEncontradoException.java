package entities.excecoes;

/**
 * Exceção lançada quando um veículo não é encontrado.
 */
public class VeiculoNaoEncontradoException extends RuntimeException {
    /**
     * Constrói uma nova instância da exceção com a mensagem especificada.
     * 
     * @param mensagem a mensagem de erro da exceção
     */
    public VeiculoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
