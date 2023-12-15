package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoTurno;
import entities.Vaga;
import entities.Enums.ETurnos;
import entities.Enums.Servicos;
import entities.interfaces.IFabrica;

/**
 * Classe responsável por criar instâncias de UsoDeVaga para o turno da manhã.
 */
public class FabricaUsoDeTurnoManha implements IFabrica<UsoDeVaga> {

    /**
     * Cria uma instância de UsoDeVaga para o turno da manhã com um serviço
     * específico.
     * 
     * @param servico O serviço associado à instância de UsoDeVaga.
     * @return A instância de UsoDeVaga criada.
     */
    @Override
    public UsoDeVaga create(Vaga vaga, Servicos servico) {
        return new UsoTurno(vaga, ETurnos.MANHA, servico);
    }
}
