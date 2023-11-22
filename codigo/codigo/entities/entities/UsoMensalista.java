package entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import entities.Enums.Servicos;

public class UsoMensalista extends UsoDeVaga {


	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago = 0;
	private Servicos servicos;
	private boolean saiu;

    public UsoMensalista(Vaga vaga) {
        super(vaga);
        saiu = false;
    }

    public double sair() {
		this.saida = LocalDateTime.now();
		int tempoPermanenciaMinutos = (int) entrada.until(saida, ChronoUnit.MINUTES);
		if (servicos != null) {
			if (tempoPermanenciaMinutos >= servicos.getTempo()) {
                saiu = true;
				return valorPago() + servicos.getValor();
			}
		}
		return valorPago();
	}

	public boolean ehDoMes(int mes) {
		return this.entrada.getMonthValue() == mes;
	}

	public double valorPago() {
		if (entrada == null || saida == null) {
			throw new IllegalArgumentException("Entrada and Saida cannot be null");
		}

		return valorPago;
	}

	public void contratarServico(Servicos servico) {
		this.servicos = servico;
	}

    
}