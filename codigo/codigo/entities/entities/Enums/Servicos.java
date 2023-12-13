package entities.Enums;
/**
 * Representa os serviços disponíveis.
 */
public enum Servicos {

    MANOBRISTA("Manobrista",5.0,0),
    LAVAGEM("Lavagem",20.0,60),
    POLIMENTO("Polimento",45.0, 0);

    private String nome;
    private Double valor;
    private int tempo;

    /**
     * Construtor da enumeração Servicos.
     * @param nome o nome do serviço
     * @param valor o valor do serviço
     * @param tempo o tempo estimado para a realização do serviço
     */
    Servicos(String nome, double valor, int tempo){
        this.nome= nome;
        this.valor = valor;
        this.tempo = tempo;   
    }

    /**
     * Obtém o nome do serviço.
     * @return o nome do serviço
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o tempo estimado para a realização do serviço.
     * @return o tempo estimado para a realização do serviço
     */
    public int getTempo() {
        return tempo;
    }
    
    /**
     * Obtém o valor do serviço.
     * @return o valor do serviço
     */
    public Double getValor() {
        return valor;
    }
  
}