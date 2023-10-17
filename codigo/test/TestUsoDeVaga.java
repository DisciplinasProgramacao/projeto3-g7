package test;

import org.junit.Before;
import org.junit.Test;

import codigo.UsoDeVaga;
import codigo.Vaga;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class TestUsoDeVaga {

    private UsoDeVaga usoDeVaga;
    private Vaga vaga;

    @Before
    public void setup() {
        vaga = new Vaga();
        usoDeVaga = new UsoDeVaga(vaga);
    }

    @Test
    public void testGetValorPagoShouldReturnZeroIfVagaIsEmpty() {
        double result = usoDeVaga.getValorPago();
        assertEquals(0.0, result, 0.01);
    }

    @Test
    public void testGetValorPagoShouldReturnValorPago() {
        vaga.disponivel();

        double valorPago = usoDeVaga.sair();
        double result = usoDeVaga.getValorPago();
        assertEquals(valorPago, result, 0.01);
    }

}
