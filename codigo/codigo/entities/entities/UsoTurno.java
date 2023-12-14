package entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import entities.Enums.Servicos;
import entities.Enums.ETurnos;

/**
 * A classe UsoTurno representa o uso de uma vaga de estacionamento por um
 * determinado período de tempo.
 * Ela herda da classe UsoDeVaga e contém informações sobre a vaga, horário de
 * entrada e saída, valor pago,
 * serviços contratados, turno, cliente e status de saída.
 */
/**
 * Representa o uso de uma vaga de estacionamento por um determinado turno.
 * Esta classe herda da classe UsoDeVaga.
 */
public class UsoTurno extends UsoDeVaga {
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago = 0;
    private Servicos servicos;
    private ETurnos turno;

    /**
     * Construtor da classe UsoTurno.
     * 
     * @param vaga a vaga de estacionamento utilizada
     */
    public UsoTurno(Vaga vaga, ETurnos turno) {
        super(vaga);
        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.turno = turno;
    }

    public UsoTurno(Vaga vaga, ETurnos turno, Servicos servicos) {
        super(vaga);
        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.turno = turno;
        this.servicos = servicos;
    }

    /**
     * Registra a saída do veículo e calcula o valor a ser pago pelo uso da vaga.
     * 
     * @return O valor a ser pago pelo uso da vaga.
     */
    @Override
    public double sair() {
        this.saida = LocalDateTime.now();
        int tempoPermanenciaMinutos = (int) entrada.until(saida, ChronoUnit.MINUTES);
        if (servicos != null && !turno.isTurno(entrada.toLocalTime(), saida.toLocalTime())) {
            if (tempoPermanenciaMinutos >= servicos.getTempo()) {
                valorPago();
            }
        }
        if (turno.isTurno(entrada.toLocalTime(), saida.toLocalTime())) {
            valorPago = super.sair();
        }
        valorPago += servicos.getValor();
        return valorPago
        ;
    }

    /**
     * Verifica se o uso da vaga ocorreu no mês especificado.
     * 
     * @param mes o número do mês
     * @return true se o uso da vaga ocorreu no mês especificado, false caso
     *         contrário
     */
    @Override
    public boolean ehDoMes(int mes) {
        return this.entrada.getMonthValue() == mes;
    }

    /**
     * Calcula o valor total pago pelo uso da vaga.
     * 
     * @return o valor total pago pelo uso da vaga
     * @throws IllegalArgumentException se a entrada ou saída forem nulas
     */
    @Override
    public double valorPago() {
        if (entrada == null || saida == null) {
            throw new IllegalArgumentException("Entrada and Saida cannot be null");
        }

        return valorPago;
    }

    /**
     * Contrata um serviço adicional para o uso da vaga.
     * 
     * @param servico o serviço a ser contratado
     */
    public Servicos contratarServico(Servicos servico) {
        this.servicos = servico;
        return servico;

    }
}
