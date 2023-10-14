import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EstacionamentoTest {
    @Test
    public void adicionarVeiculo(){
    Cliente cliente = new Cliente("Vitor","1234");
    Estacionamento estacionamento = new Estacionamento("Estacionamento", 2, 2);
    Veiculo veiculo = new Veiculo( "ABC1234");
    boolean result = estacionamento.addVeiculo(veiculo, "1234");
    assertTrue(result);
    }

    @Test
    public void adicionarCliente(){
        Estacionamento estacionamento = new Estacionamento("Estacionamento", 2, 2);
        Cliente cliente = new Cliente("Vitor","1234");
        boolean result = estacionamento.adicionarCliente(cliente);
        assertTrue(result);
    }
}
