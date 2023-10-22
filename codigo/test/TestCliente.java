package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import entities.*;

public class TestCliente {

    private Cliente cliente;
    private Veiculo veiculo;
    private Object[] veiculos;

    @Before
    public void setup() {
        cliente = new Cliente("Joao", "333");
        veiculo = new Veiculo("31314");
    }

    @Test
    public void testCliente() {
        assertNotNull(null, cliente.getNome());
        assertNotNull(null, cliente.getId());
    }

    @Test
    public void testAddVeiculo() {
        cliente.addVeiculo(veiculo);
        veiculos = cliente.getVeiculos();
        assertNotNull(null, veiculos[0]);
    }

    @Test
    public void testPossuiVeiculo() {
        veiculos = cliente.getVeiculos();
        cliente.addVeiculo(veiculo);
        Veiculo veiculoBusca = cliente.possuiVeiculo("31314");
        assertEquals(veiculoBusca, veiculos[0]);
    }


    //Nao terminado
    @Test
    public void testTotalUsos() {

        cliente.addVeiculo(veiculo);
        System.out.println(cliente.totalDeUsos());
        assertNotEquals(0, cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        double result;
        result = cliente.arrecadadoPorVeiculo("31314");
        assertNotEquals(0, result);
    }

    @Test
    public void testArrecadadoTotal() {
        double result;
        result = cliente.arrecadadoTotal();
        assertNotEquals(0, result);
    }

    @Test
    public void TestArrecadadoNoMes() {
        double result;
        result = cliente.arrecadadoNoMes(10);
        assertNotEquals(0, result);
    }




}