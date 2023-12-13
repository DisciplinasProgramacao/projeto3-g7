package entities;

import java.time.LocalDateTime;

import entities.Enums.Servicos;

public class UsoMensalista extends UsoDeVaga {

	private double valorPago = 0.0;

    public UsoMensalista(Vaga vaga) {
		super(vaga);
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
    }

	 public UsoMensalista(Vaga vaga, Servicos servicos) {
		super(vaga);
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.servicos = servicos;
    }

	 /**
     * Calcula o valor a ser pago pela permanência no local.
     *
     * @return O valor a ser pago pela permanência, com base nos registros de entrada e saída.
     * @throws IllegalArgumentException Se entrada ou saída estiverem nulas.
     */
	@Override
	public double valorPago() {
		if (entrada == null || saida == null) {
			throw new IllegalArgumentException("Entrada and Saida cannot be null");
		}

		return valorPago;
	}  
}