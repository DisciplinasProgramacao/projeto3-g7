package entities;

import java.time.LocalDateTime;

import entities.Enums.Servicos;

public class UsoHorista extends UsoDeVaga {

    public UsoHorista(Vaga vaga) {
        super(vaga);
        this.entrada = LocalDateTime.now();
    }

    public UsoHorista(Vaga vaga, Servicos servicos) {
        super(vaga);
        this.entrada = LocalDateTime.now();
        this.servicos = servicos;
    }

   
}
