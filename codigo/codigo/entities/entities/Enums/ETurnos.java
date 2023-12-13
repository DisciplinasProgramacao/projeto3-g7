package entities.Enums;

import java.time.LocalDateTime;
import java.time.LocalTime;

public enum ETurnos {

    MANHA("Manha", LocalTime.of(8, 00), LocalTime.of(12, 00)),
    TARDE("Tarde", LocalTime.of(12, 01), LocalTime.of(18, 00)),
    NOITE("Noite", LocalTime.of(18, 01), LocalTime.of(23, 59));

    private String descricao;
    private LocalTime inicio;
    private LocalTime fim;

    ETurnos(String descricao, LocalTime inicio, LocalTime fim) {
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
    }

    public boolean isTurno(LocalTime entrada, LocalTime saida) {
        return (entrada.isBefore(inicio) || saida.isAfter(fim));
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalTime getHoraInicio() {
        return inicio;
    }

    public LocalTime getHoraFim() {
        return fim;
    } 
}