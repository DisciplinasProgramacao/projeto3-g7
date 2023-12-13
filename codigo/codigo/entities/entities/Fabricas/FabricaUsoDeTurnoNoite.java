package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoTurno;
import entities.Vaga;
import entities.Enums.ETurnos;
import entities.Enums.Servicos;
import entities.interfaces.IFabrica;

/**
 * Esta classe representa uma fábrica para a criação de instâncias da classe
 * UsoDeVaga
 * com o turno de noite.
 */
public class FabricaUsoDeTurnoNoite implements IFabrica<UsoDeVaga> {
    private Vaga vaga;

    /**
     * Cria uma instância de UsoDeVaga com o turno de noite.
     * 
     * @return A instância de UsoDeVaga criada.
     */
    @Override
    public UsoDeVaga create() {
        return new UsoTurno(vaga, ETurnos.NOITE);
    }

    /**
     * Cria uma instância de UsoDeVaga com o turno de noite e o serviço
     * especificado.
     * 
     * @param servico O serviço associado à instância de UsoDeVaga.
     * @return A instância de UsoDeVaga criada.
     */
    @Override
    public UsoDeVaga create(Servicos servico) {
        return new UsoTurno(vaga, ETurnos.NOITE, servico);
    }
}
