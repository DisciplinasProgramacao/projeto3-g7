package entities.excecoes;

public class VeiculoNaoEncontradoException extends RuntimeException {
    public VeiculoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
