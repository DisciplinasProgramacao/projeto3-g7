package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoMensalista;
import entities.Vaga;
import entities.Enums.Servicos;
import entities.interfaces.IFabrica;

/**
 * Esta classe representa uma fábrica para criar instâncias de UsoDeVaga do tipo
 * Mensalista.
 * Implementa a interface IFabrica<UsoDeVaga>.
 */
public class FabricaUsoDeVagaMensalista implements IFabrica<UsoDeVaga> {

    /**
     * Cria uma nova instância de UsoDeVaga do tipo Mensalista com um serviço
     * específico.
     * 
     * @param servico O serviço associado ao UsoDeVaga.
     * @return A nova instância de UsoDeVaga.
     */
    @Override
    public UsoDeVaga create(Vaga vaga, Servicos servico) {
        return new UsoMensalista(vaga, servico);
    }

    @Override
    public UsoDeVaga create(Vaga vaga) {
        return new UsoMensalista(vaga);
    }
}