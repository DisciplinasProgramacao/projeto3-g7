package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoHorista;
import entities.Vaga;
import entities.Enums.Servicos;
import entities.interfaces.IFabrica;

public class FabricaUsoDeVagaHorista implements IFabrica<UsoDeVaga> {
    private Vaga vaga;

    @Override
    public UsoDeVaga create() {
        return new UsoHorista(vaga);
    }

    @Override
    public UsoDeVaga create(Servicos servico) {
        return new UsoHorista(vaga, servico);
    }

}
