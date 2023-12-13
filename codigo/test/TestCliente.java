package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import entities.Cliente;
import entities.Veiculo;
import entities.Vaga;
import entities.Enums.ETurnos;
import entities.Enums.ECliente;

public class TestCliente {

    @Test
    public void testAddVeiculo() {
        Cliente cliente = new Cliente("João", "123");
        Veiculo veiculo = new Veiculo("ABC123");
        cliente.addVeiculo(veiculo);
        assertEquals(veiculo, cliente.possuiVeiculo("ABC123"));
    }

    @Test
    public void testPossuiVeiculo() {
        Cliente cliente = new Cliente("João", "123");
        Veiculo veiculo = new Veiculo("ABC123");
        cliente.addVeiculo(veiculo);
        assertEquals(veiculo, cliente.possuiVeiculo("ABC123"));
        assertNull(cliente.possuiVeiculo("XYZ789"));
    }

    @Test
    public void testTotalDeUsos() {
        Cliente cliente = new Cliente("João", "123");
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("ABC124");
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        Vaga vaga = new Vaga(1, 1);
        veiculo1.estacionar(vaga);
        assertEquals(2, cliente.totalDeUsos());
    }

    @Test
    public void testClienteTipo() {
        Cliente cliente = new Cliente("João", "123", ECliente.HORISTA, ETurnos.MANHA);
        assertEquals(true, cliente.verificarTipo("Horista"));
        assertEquals(false, cliente.verificarTipo("Mensalista"));
    }

}