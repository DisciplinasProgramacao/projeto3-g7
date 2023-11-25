package entities.Enums;

import java.time.LocalDateTime;

public enum ETurnos {

    // lDate a = LocalDateTime.of(2012, 6, 30, 12, 00);
    // LocalDate b = LocalDateTime.of(2012, 7, 1, 12, 00);
    MANHA("Manha", LocalDateTime.of(2023, 11, 25, 8, 00), LocalDateTime.of(2023, 11, 25, 12, 00)),
    TARDE("Tarde", LocalDateTime.of(2023, 11, 25, 12, 01), LocalDateTime.of(2023, 11, 25, 18, 00)),
    NOITE("Noite", LocalDateTime.of(2011, 11, 25, 18, 01), LocalDateTime.of(2023, 11, 25, 23, 59));

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