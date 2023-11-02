import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import entities.Vaga;

/**
 * Esta classe contém os testes unitários para a classe Vaga.
 */
public class VagaTest {

    /**
     * Teste para o método estacionar() quando a vaga está disponível.
     */
    @Test
    public void testEstacionarVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1); // Crie uma vaga
        assertTrue(vaga.estacionar()); // Deve retornar true, pois a vaga está disponível
    }

    /**
     * Teste para o método estacionar() quando a vaga não está disponível.
     */
    @Test
    public void testEstacionarVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1); // Crie uma vaga
        vaga.disponivel = false; // Defina a vaga como não disponível
        assertFalse(vaga.estacionar()); // Deve retornar false, pois a vaga não está disponível
    }

    /**
     * Teste para o método sair() quando a vaga está disponível.
     */
    @Test
    public void testSairVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1); // Crie uma vaga
        assertTrue(vaga.sair()); // Deve retornar true, pois a vaga está disponível
    }

    /**
     * Teste para o método sair() quando a vaga não está disponível.
     */
    @Test
    public void testSairVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1); // Crie uma vaga
        vaga.disponivel = false; // Defina a vaga como não disponível
        assertTrue(vaga.sair()); // Deve retornar true, pois a vaga está disponível
    }

    /**
     * Teste para o método disponivel() quando a vaga está disponível.
     */
    @Test
    public void testDisponivelVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1); // Crie uma vaga
        vaga.disponivel = true; // Defina a vaga como disponível
        assertTrue(vaga.disponivel()); // Deve retornar true, pois a vaga está disponível
    }

    /**
     * Teste para o método disponivel() quando a vaga não está disponível.
     */
    @Test
    public void testDisponivelVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1); // Crie uma vaga
        vaga.disponivel = false; // Defina a vaga como não disponível
        assertFalse(vaga.disponivel()); // Deve retornar false, pois a vaga não está disponível
    }
}
