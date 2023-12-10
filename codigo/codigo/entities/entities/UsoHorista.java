package entities;

import java.time.LocalDateTime;

public class UsoHorista extends UsoDeVaga {

    public UsoHorista(Vaga vaga) {
        super(vaga);
		this.entrada = LocalDateTime.now();
    }
}
    