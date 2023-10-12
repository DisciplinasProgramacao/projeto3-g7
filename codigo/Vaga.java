public class Vaga {

	private String id;
	private boolean disponivel;
	private static String[] alf = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "Y", "Z" };

	public String Vaga(int fila, int numero) {
		String letraVaga = "";
		for (int i = 0; i < fila; i++) {
			letraVaga = alf[fila].toString();
		}
		String vagaFinal = letraVaga + numero;
		return vagaFinal;
	}

	public boolean estacionar() {
		if (disponivel == false) {
			return false;
		} else {
			return true;
		}
	}

	public boolean sair() {
		if (disponivel == false) {
			return true;
		} else {
			return false;
		}
	}

	public boolean disponivel() {
		return disponivel;
	}

}
