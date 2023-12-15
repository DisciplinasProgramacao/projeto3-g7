package entities.interfaces;

import entities.Vaga;
import entities.Enums.Servicos;

/**
 * Interface que representa uma fábrica genérica.
 * 
 * @param <T> o tipo de objeto que a fábrica cria.
 */
public interface IFabrica<T> {
    

    /**
     * Cria um objeto do tipo T com base no serviço fornecido.
     * 
     * @param servico o serviço a ser utilizado na criação do objeto.
     * @return o objeto criado.
     */
    public T create(Vaga vaga ,Servicos servico);

    public T create(Vaga vaga);

    

    
}
