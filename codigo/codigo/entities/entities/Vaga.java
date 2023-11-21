package entities;

public class Vaga {

	String id;
	public boolean disponivel;
	private String filas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * * Gera uma string Vaga com base nos parâmetros fila e numero.
	 * *
	 * * @param fila o parâmetro fila representa o número da fila.
	 * * @param numero o parâmetro numero representa o número da vaga.
	 * * @return uma string contendo o valor gerado da Vaga.
	 * +
	 */
	public Vaga(int fila, int numero) {
		String vagaFinal;
		String letraVaga = "";
		letraVaga += filas.charAt(fila);
		vagaFinal = letraVaga + numero;

		if(vagaFinal != null)
			disponivel = false;
	}

	/**
	 * Valida se o carro pode ser estacionado na vaga
	 *
	 * @return true se ele pode estacionar ou false se ele não pode estacionar
	 */
	public boolean estacionar() {
		if (disponivel == false) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Verifica se a vaga esta disponivel e retorna se o usuario pode sair dela
	 * 
	 * @return true se ele puder sair ou false se a vaga estiver vazia
	 */
	public boolean sair() {
		if (disponivel == false) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifica se esta disponivel
	 *
	 * @return true se estiver disponivel e false se não estiver disponivel
	 */
	public boolean disponivel() {
		if (this.disponivel != false)
			disponivel = true;
		else
			disponivel = false;
		return disponivel;
	}

}
