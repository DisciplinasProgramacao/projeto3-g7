package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoHorista;
import entities.Vaga;
import entities.Enums.Servicos;
import entities.interfaces.IFabrica;

/**
 * Esta classe representa uma fábrica para criar instâncias de UsoDeVagaHorista.
 * Implementa a interface IFabrica<UsoDeVaga>.
 */
public class FabricaUsoDeVagaHorista implements IFabrica<UsoDeVaga> {
    private Vaga vaga;

    /**
     * Cria uma nova instância de UsoDeVagaHorista.
     * 
     * @return A nova instância de UsoDeVagaHorista.
     */
    @Override
    public UsoDeVaga create() {
        return new UsoHorista(vaga);
    }

    /**
     * Cria uma nova instância de UsoDeVagaHorista com o serviço especificado.
     * 
     * @param servico O serviço associado à instância de UsoDeVagaHorista.
     * @return A nova instância de UsoDeVagaHorista.
     */
    @Override
    public UsoDeVaga create(Servicos servico) {
        return new UsoHorista(vaga, servico);
    }
}
