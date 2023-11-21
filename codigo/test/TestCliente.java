package test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import entities.Cliente;
import entities.Veiculo;

public class TestCliente {

    @Test
    public void testAddVeiculo() {
        Cliente cliente = new Cliente("John Doe", "123");
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("DEF456");

        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        Veiculo[] veiculos = cliente.getVeiculos();
        Assert.assertEquals(veiculo1, veiculos[0]);
        Assert.assertEquals(veiculo2, veiculos[1]);
    }

    @Test
    public void testGetId() {
        Cliente cliente = new Cliente("John Doe", "123");
        Assert.assertEquals("123", cliente.getId());
    }

    @Test
    public void testGetVeiculos() {
        Cliente cliente = new Cliente("John Doe", "123");
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("DEF456");

        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        Veiculo[] veiculos = cliente.getVeiculos();
        Assert.assertArrayEquals(new Veiculo[]{veiculo1, veiculo2}, veiculos);
    }

    @Test
    public void testGetQtdVeiculo() {
        Cliente cliente = new Cliente("John Doe", "123");
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("DEF456");

        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        Assert.assertEquals(2, cliente.getQtdVeiculo());
    }

    @Test
    public void testPossuiVeiculo() {
        Cliente cliente = new Cliente("John Doe", "123");
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("DEF456");

        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        Assert.assertEquals(veiculo1, cliente.possuiVeiculo("ABC123"));
        Assert.assertNotEquals(veiculo1, cliente.possuiVeiculo("XYZ789"));
    }



    // @Test
    // public void testHistoricoCliente() {
    //     Cliente cliente = new Cliente("John Doe", "123");
    //     Veiculo veiculo1 = new Veiculo("ABC123");
    //     Veiculo veiculo2 = new Veiculo("DEF456");

    //     veiculo1.addArrecadacao(100);
    //     veiculo2.addArrecadacao(200);

    //     cliente.addVeiculo(veiculo1);
    //     cliente.addVeiculo(veiculo2);

    //     String expectedHistorico = "Histórico do Cliente: John Doe (ID: 123)\n" +
    //             "Veículos do Cliente e Valores Gastos:\n" +
    //             "Veículo 1: R$ 100.0\n" +
    //             "Veículo 2: R$ 200.0\n" +
    //             "Valor Gasto por Mês:\n" +
    //             "Mês 1: R$ 100.0\n" +
    //             "Mês 2: R$ 200.0\n" +
    //             "Mês 3: R$ 0.0\n" +
    //             "Mês 4: R$ 0.0\n" +
    //             "Mês 5: R$ 0.0\n" +
    //             "Mês 6: R$ 0.0\n" +
    //             "Mês 7: R$ 0.0\n" +
    //             "Mês 8: R$ 0.0\n" +
    //             "Mês 9: R$ 0.0\n" +
    //             "Mês 10: R$ 0.0\n" +
    //             "Mês 11: R$ 0.0\n" +
    //             "Mês 12: R$ 0.0\n" +
    //             "Valor Total Arrecadado: R$ 300.0\n";

    //     Assert.assertEquals(expectedHistorico, cliente.historicoCliente());
    // }
}