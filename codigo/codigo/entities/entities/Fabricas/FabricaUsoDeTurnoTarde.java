package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoTurno;
import entities.Vaga;
import entities.Enums.ETurnos;
import entities.Enums.Servicos;
import entities.interfaces.IFabrica;

/**
 * Esta classe representa uma fábrica para a criação de objetos do tipo
 * UsoDeVaga
 * com turno de tarde.
 */
public class FabricaUsoDeTurnoTarde implements IFabrica<UsoDeVaga> {
    private Vaga vaga;

    /**
     * Cria um objeto UsoDeVaga com turno de tarde.
     * 
     * @return O objeto UsoDeVaga criado.
     */
    @Override
    public UsoDeVaga create() {
        return new UsoTurno(vaga, ETurnos.TARDE);
    }

    /**
     * Cria um objeto UsoDeVaga com turno de tarde e um serviço específico.
     * 
     * @param servico O serviço associado ao objeto UsoDeVaga.
     * @return O objeto UsoDeVaga criado.
     */
    @Override
    public UsoDeVaga create(Servicos servico) {
        return new UsoTurno(vaga, ETurnos.TARDE, servico);
    }
}
