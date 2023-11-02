package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import entities.Vaga;

public class VagaTest {

    @Test
    public void testEstacionarVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        assertTrue(vaga.estacionar());
    }

    @Test
    public void testEstacionarVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        vaga.disponivel = false;
        assertFalse(vaga.estacionar());
    }

    @Test
    public void testSairVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        assertTrue(vaga.sair());
    }

    @Test
    public void testSairVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        vaga.disponivel = false;
        assertTrue(vaga.sair());
    }

    @Test
    public void testDisponivelVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        vaga.disponivel = true;
        assertTrue(vaga.disponivel());
    }

    @Test
    public void testDisponivelVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        vaga.disponivel = false;
        assertFalse(vaga.disponivel());
    }
}
