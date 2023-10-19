package entities;

public enum Servicos {
    MANOBRISTA(5.0, "manobrista", 0.0),
    LAVAGEM(20.0, "lavagem", 1.0),
    POLIMENTO(45.0, "polimento", 2.0);

    private double valor;
    private String descricao;
    private double minPermanencia;

    private Servicos(double valor, String descricao, double minPermanencia) {
        this.valor = valor;
        this.descricao = descricao;
        this.minPermanencia = minPermanencia;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMinPermanencia() {
        return minPermanencia;
    }

    public double calculateValue(double tempoPermanencia) {
        double valorAdicional = 0.0;

        if (tempoPermanencia < minPermanencia) {
            valorAdicional = (minPermanencia - tempoPermanencia) * 10.0;
        }

        return valor + valorAdicional;
    }
}