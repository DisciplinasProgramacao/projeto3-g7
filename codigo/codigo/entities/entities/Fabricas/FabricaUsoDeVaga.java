package entities.Fabricas;

import java.util.HashMap;
import java.util.Map;

import entities.UsoDeVaga;
import entities.interfaces.IFabrica;

public class FabricaUsoDeVaga {
    private Map<String, IFabrica<UsoDeVaga>> fabricas;

    public FabricaUsoDeVaga() {
        fabricas = new HashMap<>();
        fabricas.put("TARDE", new FabricaUsoDeTurnoTarde());
        fabricas.put("NOITE", new FabricaUsoDeTurnoNoite());
        fabricas.put("MANHA", new FabricaUsoDeTurnoManha());
        fabricas.put("horista", new FabricaUsoDeVagaHorista());
        fabricas.put("mensalista", new FabricaUsoDeVagaMensalista());
    }
}
