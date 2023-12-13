package entities.Fabricas;

import java.util.HashMap;
import java.util.Map;

import entities.UsoDeVaga;
import entities.interfaces.IFabrica;

/**
 * Classe responsável por criar instâncias de objetos UsoDeVaga.
 * Essa classe possui um mapa de fábricas que são utilizadas para criar
 * instâncias específicas de UsoDeVaga,
 * com base em uma determinada chave.
 */
public class FabricaUsoDeVaga {
    private Map<String, IFabrica<UsoDeVaga>> fabricas;

    /**
     * Construtor da classe FabricaUsoDeVaga.
     * Inicializa o mapa de fábricas e adiciona as fábricas correspondentes a cada
     * tipo de UsoDeVaga.
     */
    public FabricaUsoDeVaga() {
        fabricas = new HashMap<>();
        fabricas.put("TARDE", new FabricaUsoDeTurnoTarde());
        fabricas.put("NOITE", new FabricaUsoDeTurnoNoite());
        fabricas.put("MANHA", new FabricaUsoDeTurnoManha());
        fabricas.put("horista", new FabricaUsoDeVagaHorista());
        fabricas.put("mensalista", new FabricaUsoDeVagaMensalista());
    }
}
