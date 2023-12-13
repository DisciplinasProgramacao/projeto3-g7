package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoTurno;
import entities.Vaga;
import entities.Enums.ETurnos;
import entities.Enums.Servicos;
import entities.interfaces.IFabrica;

public class FabricaUsoDeTurnoNoite implements IFabrica<UsoDeVaga> {
    private Vaga vaga;

    @Override
    public UsoDeVaga create() {
        return new UsoTurno(vaga, ETurnos.NOITE);
    }

     @Override
    public UsoDeVaga create(Servicos servico) {
        return new UsoTurno(vaga, ETurnos.NOITE, servico);
    }

}
