import java.util.Scanner;
import entities.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento(null, 0, 0);

        boolean sair = false;

        while (!sair) {
            exibirMenu();
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    registrarVeiculo(sc, estacionamento);
                    break;
                case 2:
                    realizarSaidaVeiculo(sc, estacionamento);
                    break;
                case 3:
                    consultarCliente(sc, estacionamento);
                    break;
                case 4:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        sc.close();
    }

    public static void exibirMenu() {
        System.out.println("===== MENU DO ESTACIONAMENTO =====");
        System.out.println("1. Entrada de Veículo");
        System.out.println("2. Saída de Veículo");
        System.out.println("3. Consultar Cliente");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void registrarVeiculo(Scanner sc, Estacionamento estacionamento) {
        sc.nextLine();
        System.out.print("Placa do Veículo: ");
        String placa = sc.nextLine();
        System.out.print("Nome do Cliente: ");
        String nomeCliente = sc.nextLine();
        System.out.print("ID do Cliente: ");
        String idCliente = sc.nextLine();

        Cliente cliente = new Cliente(nomeCliente, idCliente);
        Veiculo veiculo = new Veiculo(placa);

        cliente.addVeiculo(veiculo);
        estacionamento.addVeiculo(veiculo, cliente);

        System.out.println("Veículo registrado no estacionamento.");
    }

    public static void realizarSaidaVeiculo(Scanner sc, Estacionamento estacionamento) {
        sc.nextLine();
        System.out.print("Placa do Veículo que está saindo: ");
        String placaSaida = sc.nextLine();
        double valorPago = estacionamento.saidaVeiculo(placaSaida);

        if (valorPago >= 0) {
            System.out.println("Veículo saiu. Valor a ser pago: $" + valorPago);
        } else {
            System.out.println("Veículo não encontrado no estacionamento.");
        }
    }

    public static void consultarCliente(Scanner sc, Estacionamento estacionamento) {
        sc.nextLine();
        System.out.print("ID do Cliente para consulta: ");
        String idConsulta = sc.nextLine();
        Cliente clienteConsultado = estacionamento.consultarCliente(idConsulta);

        if (clienteConsultado != null) {
            System.out.println(clienteConsultado.historicoCliente());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }





    // Gerar aleatoriamente um mínimo de 50 usos de vagas distribuídas para clientes
    // e estacionamentos;
    public void carregarDados() {
        Estacionamento[] estacionamentos = new Estacionamento[3];
        estacionamentos[0] = new Estacionamento("Estacionamento1", 4, 5);
        estacionamentos[1] = new Estacionamento("Estacionamento2", 5, 6);
        estacionamentos[2] = new Estacionamento("Estacionamento3", 4, 5);
    
        Cliente[] clientes = new Cliente[3];
        clientes[0] = new Cliente("Cliente1", "1");
        clientes[1] = new Cliente("Cliente2", "2");
        clientes[2] = new Cliente("Cliente3", "3");
    
        Veiculo[] veiculos = new Veiculo[3];
        veiculos[0] = new Veiculo("1");
        veiculos[1] = new Veiculo("2");
        veiculos[2] = new Veiculo("3");
    
        for (Estacionamento estacionamento : estacionamentos) {
            for (Cliente cliente : clientes) {
                estacionamento.addCliente(cliente);
            }
        }
        for (int i = 0; i < veiculos.length; i++) {
            Cliente cliente = clientes[i];
            Veiculo veiculo = veiculos[i];
            cliente.addVeiculo(veiculo);
            estacionamentos[i].addVeiculo(veiculo, cliente);
        }
    }

    
    
    
}


