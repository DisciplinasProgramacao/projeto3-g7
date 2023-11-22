package entities;

import entities.Enums.Turnos;

/**
 * Representa o uso de uma vaga de estacionamento em um período de tempo
 * específico (turno).
 * Estende a classe UsoDeVaga.
 */
public class UsoTurno extends UsoDeVaga {
Turnos turno;
    private static final double PRECO_MENSAL = 200.0;

    /**
     * Constrói um objeto UsoTurno com a vaga de estacionamento especificada.
     * 
     * @param vaga A vaga de estacionamento a ser utilizada.
     */
    public UsoTurno(Vaga vaga) {
        super(vaga);
    }

    /**
     * Calcula o valor a ser pago pelo uso do estacionamento.
     * 
     * @return O valor total a ser pago pelo Cliente do tipo Turno.
     */
    @Override
    public double valorPago() {
        double valorPago = 0;
        if (getTurno().toString() == turno.getNome()) {
            valorPago = turno.getValor();
        }
        return valorPago;
    }

}
