package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import entities.*;

import static org.junit.Assert.*;

public class TestCliente {

    @Test
    public void testPossuiVeiculo() {
        Cliente cliente = new Cliente("Maria", "67890");
        Veiculo veiculo = new Veiculo("XYZ789");
        cliente.addVeiculo(veiculo);
        assertTrue(cliente.possuiVeiculo("XYZ789").equals(veiculo));
    }

    @Test
    public void testTotalDeUsos() {
        Cliente cliente = new Cliente("Pedro", "54321");
        Veiculo veiculo1 = new Veiculo("DEF456");
        Veiculo veiculo2 = new Veiculo("GHI789");
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(veiculo1.totalDeUsos() + veiculo2.totalDeUsos(), cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        Cliente cliente = new Cliente("Ana", "98765");
        Veiculo veiculo = new Veiculo(
            "JKL012");
        cliente.addVeiculo(veiculo);
        double valorEsperado = veiculo.totalArrecadado();
        assertEquals(valorEsperado, cliente.arrecadadoPorVeiculo("JKL012"), 0.001);
    }

    @Test
    public void testArrecadadoTotal() {
        Cliente cliente = new Cliente("Carlos", "13579");
        Veiculo veiculo1 = new Veiculo("MNO345");
        Veiculo veiculo2 = new Veiculo("PQR678");
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        double valorEsperado = veiculo1.totalArrecadado() + veiculo2.totalArrecadado();
        assertEquals(valorEsperado, cliente.arrecadadoTotal(), 0.001);
    }

    @Test
    public void testArrecadadoNoMes() {
        Cliente cliente = new Cliente("Laura", "86420");
        Veiculo veiculo = new Veiculo("STU901");
        cliente.addVeiculo(veiculo);
        int mes = 10; // Exemplo de mês
        double valorEsperado = veiculo.arrecadadoNoMes(mes);
        assertEquals(valorEsperado, cliente.arrecadadoNoMes(mes), 0.001);
    }
}