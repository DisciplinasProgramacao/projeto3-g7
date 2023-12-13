import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.UsoDeVaga;
import entities.Enums.Servicos;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class TestUsoDeVaga {

    @Test
    public void testEhDoMes() {
        UsoDeVaga usoDeVaga = new UsoDeVaga(LocalDateTime.of(2022, 1, 15, 10, 0), LocalDateTime.of(2022, 1, 15, 12, 0));

        Assertions.assertTrue(usoDeVaga.ehDoMes(1));
        Assertions.assertFalse(usoDeVaga.ehDoMes(2));
    }

    @Test
    public void testTotalDeUsosNoMes() {
        UsoDeVaga usoDeVaga1 = new UsoDeVaga(LocalDateTime.of(2022, 1, 15, 10, 0),
                LocalDateTime.of(2022, 1, 15, 12, 0));
        UsoDeVaga usoDeVaga2 = new UsoDeVaga(LocalDateTime.of(2022, 1, 20, 14, 0),
                LocalDateTime.of(2022, 1, 20, 16, 0));
        UsoDeVaga usoDeVaga3 = new UsoDeVaga(LocalDateTime.of(2022, 2, 5, 9, 0), LocalDateTime.of(2022, 2, 5, 11, 0));

        Stream<UsoDeVaga> usos = Stream.of(usoDeVaga1, usoDeVaga2, usoDeVaga3);

        Assertions.assertEquals(2, usoDeVaga1.totalDeUsosNoMes(usos, 1));
        Assertions.assertEquals(1, usoDeVaga1.totalDeUsosNoMes(usos, 2));
    }

    @Test
    public void testValorPago() {
        UsoDeVaga usoDeVaga = new UsoDeVaga(LocalDateTime.of(2022, 1, 15, 10, 0), LocalDateTime.of(2022, 1, 15, 12, 0));

        double valorPago = usoDeVaga.valorPago();

        Assertions.assertEquals(8.0, valorPago);
    }

    @Test
    public void testContratarServico() {
        UsoDeVaga usoDeVaga = new UsoDeVaga(LocalDateTime.of(2022, 1, 15, 10, 0), LocalDateTime.of(2022, 1, 15, 12, 0));
        Servicos servico = new Servicos("Lavagem", 10.0);

        Servicos contratado = usoDeVaga.contratarServico(servico);

        Assertions.assertEquals(servico, contratado);
    }
}