package entities.Enums;

/**
 * Enumeração para representar diferentes tipos de clientes.
 */
/**
 * Representa um cliente mensalista.
 */
public enum ECliente {

    HORISTA("Horista", 0, 0.0),
    TURNO("Turno", 200.0, 0),
    MENSALISTA("Mensalista", 500.0, 0);

    /**
     * Nome do cliente.
     */
    private String nome;

    /**
     * Valor associado ao cliente.
     */
    private Double valor;

    /**
     * Construtor para a enumeração ECliente.
     *
     * @param nome    O nome do cliente.
     * @param valor   O valor associado ao cliente.
     * @param horario O horário associado ao cliente.
     */
    ECliente(String nome, double valor, double horario) {
        this.nome = nome;
        this.valor = valor;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o valor associado ao cliente.
     *
     * @return O valor associado ao cliente.
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Retorna o horário associado ao cliente.
     *
     * @return O horário associado ao cliente.
     */
    public double getHorario() {
        return valor;
    }

    /**
     * Define o valor associado ao cliente.
     *
     * @param valor O novo valor.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o horário associado ao cliente.
     *
     * @param horario O novo horário.
     */
    public void setHorario(double horario) {
        this.valor = horario;
    }

}