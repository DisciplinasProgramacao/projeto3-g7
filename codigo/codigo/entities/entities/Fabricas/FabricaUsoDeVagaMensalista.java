package entities.Fabricas;

import java.security.Provider.Service;

import entities.UsoDeVaga;
import entities.UsoMensalista;
import entities.Vaga;
import entities.Enums.Servicos;
import entities.interfaces.IFabrica;

public class FabricaUsoDeVagaMensalista implements IFabrica<UsoDeVaga> {
    private Vaga vaga;

    @Override
    public UsoDeVaga create() {
        return new UsoMensalista(vaga);
    }

    
    @Override
    public UsoDeVaga create(Servicos servico) {
        return new UsoMensalista(vaga, servico);
    }

}