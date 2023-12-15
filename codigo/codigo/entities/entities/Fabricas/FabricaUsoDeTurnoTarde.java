package entities.Fabricas;

import entities.UsoDeVaga;
import entities.Vaga;
import entities.Enums.ETurnos;
import entities.Enums.Servicos;
import entities.UsoTurno;
import entities.interfaces.IFabrica;

/**
 * Esta classe representa uma fábrica para a criação de objetos do tipo
 * UsoDeVaga
 * com turno de tarde.
 */
public class FabricaUsoDeTurnoTarde implements IFabrica<UsoDeVaga> {
    /**
     * Cria uma nova instância de UsoDeVaga do tipo Mensalista com um serviço
     * específico.
     * 
     * @param servico O serviço associado ao UsoDeVaga.
     * @return A nova instância de UsoDeVaga.
     */
    @Override
    public UsoDeVaga create(Vaga vaga ,Servicos servico) {
        return new UsoTurno(vaga, ETurnos.TARDE, servico);
    }
}
