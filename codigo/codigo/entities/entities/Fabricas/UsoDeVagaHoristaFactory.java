package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoHorista;
import entities.Vaga;
import entities.interfaces.UsoDeVagaFactory;

/**
 * Uma classe de fábrica para criar instâncias de UsoDeVagaHorista.
 */

public class UsoDeVagaHoristaFactory implements UsoDeVagaFactory {
    @Override
    public UsoDeVaga criarUsoDeVaga(Vaga vaga) {
        return new UsoHorista(vaga);
    }

}