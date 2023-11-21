package entities.Enums;

import entities.Data;

public enum Turno {

    MANHA("Manha", 08.00, 12.00),
    TARDE("Tarde", 12.01, 18.00),
    NOITE("Noite", 18.01, 23.59);

    private String nome;
    private Data horaInicio;
    private Data horaFim;

    Turno(String nome, Data horaInicio, Data horaFim) {
        this.nome = nome;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public String getNome() {
        return nome;
    }

    public Data getHoraInicio() {
        return horaInicio;
    }

    public Data getHoraFim() {
        return horaFim;
    }
}
