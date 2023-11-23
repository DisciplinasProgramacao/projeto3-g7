package entities.Enums;

import entities.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

public enum ETurnos {


//     lDate a = LocalDateTime.of(2012, 6, 30, 12, 00);
//    LocalDate b = LocalDateTime.of(2012, 7, 1, 12, 00);
    MANHA("Manha",  LocalDateTime.of(0,0,0, 8 , 00), LocalDateTime.of(0,0,0, 12 , 00)),
    TARDE("Tarde", LocalDateTime.of(0,0,0, 12 , 01), LocalDateTime.of(0,0,0, 18 , 00)),
    NOITE("Noite",  LocalDateTime.of(0,0,0, 18 , 01),  LocalDateTime.of(0,0,0, 23 , 59));

    private String nome;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;

    ETurnos(String nome, LocalDateTime horaInicio, LocalDateTime horaFim) {
        this.nome = nome;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public LocalDateTime getHoraFim() {
        return horaFim;
    }
}
