package test;

import org.junit.Test;

import entities.UsoDeVaga;

import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class TestUsoDeVaga {
    private static final double DELTA = 0.001;

    @Test
    public void testValorPagoWithValidInput() {
        LocalDateTime entrada = LocalDateTime.of(2022, 1, 1, 10, 0);
        LocalDateTime saida = LocalDateTime.of(2022, 1, 1, 11, 30);
        UsoDeVaga usoDeVaga = new UsoDeVaga(entrada, saida);
        double valorPago = usoDeVaga.valorPago();
        assertEquals(24.0, valorPago, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValorPagoWithNullEntrada() {
        LocalDateTime saida = LocalDateTime.of(2022, 1, 1, 11, 30);
        UsoDeVaga usoDeVaga = new UsoDeVaga(null, saida);
        usoDeVaga.valorPago();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValorPagoWithNullSaida() {
        LocalDateTime entrada = LocalDateTime.of(2022, 1, 1, 10, 0);
        UsoDeVaga usoDeVaga = new UsoDeVaga(entrada, null);
        usoDeVaga.valorPago();
    }

    @Test
    public void testValorPagoWithZeroMinutes() {
        LocalDateTime entrada = LocalDateTime.of(2022, 1, 1, 10, 0);
        LocalDateTime saida = LocalDateTime.of(2022, 1, 1, 10, 0);
        UsoDeVaga usoDeVaga = new UsoDeVaga(entrada, saida);
        double valorPago = usoDeVaga.valorPago();
        assertEquals(4.0, valorPago, DELTA);
    }

    @Test
    public void testValorPagoWithMaxValue() {
        LocalDateTime entrada = LocalDateTime.of(2022, 1, 1, 10, 0);
        LocalDateTime saida = LocalDateTime.of(2022, 1, 1, 13, 7);
        UsoDeVaga usoDeVaga = new UsoDeVaga(entrada, saida);
        double valorPago = usoDeVaga.valorPago();
        assertEquals(50.0, valorPago, DELTA);
    }
}