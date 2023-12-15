package entities.Enums;

import java.time.LocalTime;

/**
 * Representa os turnos disponíveis.
 */
public enum ETurnos {

    /**
     * Turno da manhã, das 8:00 às 12:00.
     */
    MANHA("Manha", LocalTime.of(0, 00), LocalTime.of(12, 00)),

    /**
     * Turno da tarde, das 12:01 às 18:00.
     */
    TARDE("Tarde", LocalTime.of(12, 01), LocalTime.of(18, 00)),

    /**
     * Turno da noite, das 18:01 às 23:59.
     */
    NOITE("Noite", LocalTime.of(18, 01), LocalTime.of(23, 59));

    private String descricao;
    private LocalTime inicio;
    private LocalTime fim;

    ETurnos(String descricao, LocalTime inicio, LocalTime fim) {
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Verifica se um intervalo de tempo está dentro do turno.
     * 
     * @param entrada a hora de entrada
     * @param saida a hora de saída
     * @return true se o intervalo está dentro do turno, false caso contrário
     */
    public boolean isTurno(LocalTime entrada, LocalTime saida) {
        return (entrada.isBefore(inicio) || saida.isAfter(fim));
    }

    /**
     * Obtém a descrição do turno.
     * 
     * @return a descrição do turno
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém a hora de início do turno.
     * 
     * @return a hora de início do turno
     */
    public LocalTime getHoraInicio() {
        return inicio;
    }

    /**
     * Obtém a hora de fim do turno.
     * 
     * @return a hora de fim do turno
     */
    public LocalTime getHoraFim() {
        return fim;
    } 
}