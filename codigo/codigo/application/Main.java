import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Locale;
import entities.Estacionamento;
import entities.Vaga;
import entities.Veiculo;
import entities.Cliente;
import entities.UsoDeVaga;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

       // Criação de um objeto Vaga
        Vaga vaga = new Vaga();
        
        // Criação de um objeto UsoDeVaga
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);
        
        // Chamada do método sair e impressão do valor pago
        double valorPago = usoDeVaga.sair();
        System.out.println("Valor pago: " + valorPago);
        
        // Verificação se o uso da vaga ocorreu no mês atual
        int mesAtual = LocalDateTime.now().getMonthValue();
        boolean ehDoMes = usoDeVaga.ehDoMes(mesAtual);
        System.out.println("O uso da vaga ocorreu no mês atual? " + ehDoMes);
        
        // Obtenção e impressão do valor pago pelo uso da vaga
        double valorPagoObtido = usoDeVaga.getValorPago();
        System.out.println("Valor pago obtido: " + valorPagoObtido);

        // Criação de um objeto Cliente
        Cliente cliente = new Cliente("Nome do Cliente", "ID do Cliente");
        
        // Criação de um objeto Veiculo
        Veiculo veiculo = new Veiculo("Placa do Veiculo");
        
        // Adicionando o veículo ao cliente
        cliente.addVeiculo(veiculo);
        
        // Verificando se o cliente possui o veículo
        Veiculo veiculoEncontrado = cliente.possuiVeiculo("Placa do Veiculo");
        System.out.println("O cliente possui o veículo? " + (veiculoEncontrado != null));
        
        // Obtendo o total de usos dos veículos do cliente
        int totalDeUsos = cliente.totalDeUsos();
        System.out.println("Total de usos dos veículos do cliente: " + totalDeUsos);
        
        // Obtendo o valor arrecadado por um veículo
        double valorArrecadadoPorVeiculo = cliente.arrecadadoPorVeiculo("Placa do Veiculo");
        System.out.println("Valor arrecadado pelo veículo: " + valorArrecadadoPorVeiculo);
        
        // Obtendo o valor arrecadado total pelos veículos do cliente
        double valorArrecadadoTotal = cliente.arrecadadoTotal();
        System.out.println("Valor arrecadado total pelos veículos do cliente: " + valorArrecadadoTotal);
        
        // Obtendo o valor arrecadado pelos veículos do cliente em um mês específico
        int mes = 1;  // Janeiro
        double valorArrecadadoNoMes = cliente.arrecadadoNoMes(mes);
        System.out.println("Valor arrecadado pelos veículos do cliente no mês " + mes + ": " + valorArrecadadoNoMes);

        // Criação de um objeto Veiculo
        Veiculo veiculo = new Veiculo("Placa do Veiculo", "ID do Veiculo");
        
        // Criação de um objeto Vaga
        Vaga vaga = new Vaga();
        
        // Estacionando o veículo
        veiculo.estacionar(vaga);
        
        // Removendo o veículo do estacionamento e obtendo o valor a ser pago
        double valorPago = veiculo.sair();
        System.out.println("Valor pago: " + valorPago);
        
        // Obtendo o total arrecadado pelo veículo
        double totalArrecadado = veiculo.totalArrecadado();
        System.out.println("Total arrecadado pelo veículo: " + totalArrecadado);
        
        // Obtendo o valor arrecadado pelo veículo em um mês específico
        int mes = 1;  // Janeiro
        double valorArrecadadoNoMes = veiculo.arrecadadoNoMes(mes);
        System.out.println("Valor arrecadado pelo veículo no mês " + mes + ": " + valorArrecadadoNoMes);
        
        // Obtendo o total de usos do veículo
        int totalDeUsos = veiculo.totalDeUsos();
        System.out.println("Total de usos do veículo: " + totalDeUsos);


    
        System.out.print("Nome do estacionamento: ");
        String nome = sc.nextLine();
        System.out.print("Quantidade de fileiras: ");
        int fileiras = sc.nextInt();
        System.out.print("Quantidade de vagas por fileira: ");
        int vagasPorFila = sc.nextInt();

        Estacionamento estacionamento = new Estacionamento(nome, fileiras, vagasPorFila);

        Cliente cliente1 = new Cliente("Cliente1", nome);
        estacionamento.addCliente(cliente1);

        Veiculo veiculo1 = new Veiculo("Placa123", "Modelo1");
        estacionamento.addVeiculo(veiculo1, cliente1.getId());

        Vaga vaga = new Vaga();
        vaga.id = "ID da vaga";
        vaga.disponivel = true;

        estacionamento.estacionar("Placa123");

        double valorPago = estacionamento.sair("Placa123");
        System.out.println("Valor a ser pago: $" + valorPago);

        double totalArrecadado = estacionamento.totalArrecadado();
        System.out.println("Total arrecadado: $" + totalArrecadado);

        sc.close();
    }
}
