package codigo;

public enum servicos {
    MANOBRISTA(5.0, "manobrista", 0.0),
    LAVAGEM(20.0, "lavagem", 1.0),
    POLIMENTO(45.0, "polimento", 2.0);

    private double value;
    private String description;
    private double minPermanencia;

    private servicos(double value, String description, double minPermanencia) {
        this.value = value;
        this.description = description;
        this.minPermanencia = minPermanencia;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public double getMinPermanencia() {
        return minPermanencia;
    }

    public double calculateValue(double tempoPermanencia) {
        double valorAdicional = 0.0;

        if (tempoPermanencia < minPermanencia) {
            valorAdicional = (minPermanencia - tempoPermanencia) * 10.0;
        }

        return value + valorAdicional;
    }
}
