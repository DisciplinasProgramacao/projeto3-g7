package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import codigo.UsoDeVaga;
import codigo.Vaga;
import codigo.Veiculo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TestUsoDeVaga {

    private Vaga vaga;
    private Veiculo veiculo;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double VALOR_FRACAO = 10;
    private double VALOR_MAXIMO = 50;
    private double valorPago;

    @Before
    public void setup() {
        UsoDeVaga vagas = new UsoDeVaga(vaga);
        veiculo = new Veiculo(null);
        entrada = LocalDateTime.now();
        saida = entrada.plusHours(1);
        valorPago = 0;
    }

    @Test
    public void testSairSuccess() {
        UsoDeVaga vagas = new UsoDeVaga(vaga);
        vagas.sair();
        double expected = 10;

        double result = veiculo.sair();

        assertEquals(expected, result, 0);
        assertEquals(saida, veiculo.sair());
    }

}