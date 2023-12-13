package entities; 

import entities.interfaces.UsoDeVagaFactory;

/**
 * Classe responsável por criar instâncias de UsoDeVaga do tipo Horista.
 */
public class UsoDeVagaHoristaFactory implements UsoDeVagaFactory {
    @Override

    public UsoDeVaga criarUsoDeVaga(Vaga vaga) {
        return new UsoHorista(vaga);
    }
    
}