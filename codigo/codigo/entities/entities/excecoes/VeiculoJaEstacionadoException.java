package entities.excecoes;

/**
 * Exceção lançada quando um veículo já está estacionado.
 */
public class VeiculoJaEstacionadoException extends RuntimeException {
    /**
     * Cria uma nova instância da exceção com a mensagem especificada.
     * 
     * @param mensagem a mensagem de erro da exceção
     */
    public VeiculoJaEstacionadoException(String mensagem) {
        super(mensagem);
    }
}
