package entities.Fabricas;

import entities.UsoDeVaga;
import entities.UsoMensalista;
import entities.Vaga;
import entities.interfaces.IFabrica;

public class FabricaUsoDeTurnoMensalista implements IFabrica<UsoDeVaga> {
    private Vaga vaga;

    @Override
    public UsoDeVaga create() {
        return new UsoMensalista(vaga);
    }

}