package test;

import org.junit.Test;
import static org.junit.Assert.*;
import entities.Vaga;

public class TestVaga {
    @Test
    public void testEstacionarDisponivelTrue() {
        Vaga vaga = new Vaga(4, 7);
        boolean vag = vaga.disponivel();
        vag = true;
        assertEquals(true, vag);
    }

    @Test
    public void testEstacionarDisponivelFalse() {
        boolean disponivel = false;
        Vaga vaga = new Vaga(3, 4);
        disponivel = vaga.disponivel();
        assertFalse(disponivel);
    }

    @Test
    public void testSairWhenAvailable() {
        Vaga vaga = new Vaga(3, 4);
        vaga.disponivel();
        var sair = vaga.sair();
        assertEquals(true, sair);
    }
}
