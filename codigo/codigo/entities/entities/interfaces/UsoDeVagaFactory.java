package entities.interfaces;

import entities.UsoDeVaga;
import entities.UsoDeVagaHoristaFactory;
import entities.Vaga;

public interface UsoDeVagaFactory {

    /**
     * Cria um uso de vaga para a vaga passada como parâmetro.
     * 
     * @param vaga Vaga para a qual o uso de vaga será criado.
     * @return Uso de vaga criado.
     */
    public UsoDeVaga criarUsoDeVaga(Vaga vaga);

    // Método estático para criar instâncias da fábrica

    // Método estático para criar instâncias da fábrica
    static UsoDeVagaFactory criarHoristaFactory() {
        return new UsoDeVagaHoristaFactory();
    }


    
}


