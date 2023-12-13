package entities;

import java.time.LocalDateTime;

import entities.Enums.Servicos;

/**
 * Classe que representa o uso horista de uma vaga de estacionamento.
 * Herda da classe UsoDeVaga.
 */
public class UsoHorista extends UsoDeVaga {

    /**
     * Construtor da classe UsoHorista que recebe uma vaga como parâmetro.
     * Define a data e hora de entrada como o momento atual.
     * 
     * @param vaga A vaga de estacionamento utilizada.
     */
    public UsoHorista(Vaga vaga) {
        super(vaga);
        this.entrada = LocalDateTime.now();
    }

    /**
     * Construtor da classe UsoHorista que recebe uma vaga e um serviço como parâmetros.
     * Define a data e hora de entrada como o momento atual e o serviço utilizado.
     * 
     * @param vaga A vaga de estacionamento utilizada.
     * @param servicos O serviço utilizado durante o uso horista.
     */
    public UsoHorista(Vaga vaga, Servicos servicos) {
        super(vaga);
        this.entrada = LocalDateTime.now();
        this.servicos = servicos;
    }

   
}
