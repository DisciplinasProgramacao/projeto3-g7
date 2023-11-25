package entities.Enums;

public enum ECliente {

    HORISTA("Horista", 0, 0.0),
    TURNO("Turno", 200.0, 0),
    MENSALISTA("Mensalista", 500.0, 0),;

    private String nome;
    private Double valor;

    ECliente(String nome, double valor, double horario) {

        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public double getHorario() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setHorario(double horario) {
        this.valor = horario;
    }
}