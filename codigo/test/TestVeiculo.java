package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import entities.*;

public class TestVeiculo {
    @Test
    public void estacionar() {
        Vaga vaga = new Vaga();
        Veiculo veiculo = new Veiculo("1234");
        veiculo.estacionar(vaga);
    }

    @Test
    public void sair() {
        Vaga vaga = new Vaga();
        Veiculo veiculo = new Veiculo("1234");
        veiculo.estacionar(vaga);
        veiculo.sair();
    }

    @Test
    public void totalArrecadado() {
        Vaga vaga = new Vaga();
        Veiculo veiculo = new Veiculo("1234");
        veiculo.estacionar(vaga);
        veiculo.sair();
        veiculo.totalArrecadado();
    }

    @Test
    public void arrecadadoNoMes() {
        Vaga vaga = new Vaga();
        Veiculo veiculo = new Veiculo("1234");
        veiculo.estacionar(vaga);
        veiculo.sair();
        veiculo.arrecadadoNoMes(1);
    }

    @Test
    public void totalDeUsos() {
        Vaga vaga = new Vaga();
        Veiculo veiculo = new Veiculo("1234");
        veiculo.estacionar(vaga);
        veiculo.sair();
        veiculo.totalDeUsos();
    }
}