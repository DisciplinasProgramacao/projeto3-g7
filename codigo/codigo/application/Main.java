import java.util.Scanner;
import entities.Estacionamento;
import entities.Veiculo;
import entities.Cliente;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento(null, 0, 0);

        boolean sair = false;

        while (!sair) {
            System.out.println("===== MENU DO ESTACIONAMENTO =====");
            System.out.println("1. Entrada de Veículo");
            System.out.println("2. Saída de Veículo");
            System.out.println("3. Consultar Cliente");
            System.out.println("4. Consultar Arrecadação Total");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    sc.nextLine();
                    System.out.print("Placa do Veículo: ");
                    String placa = sc.nextLine();
                    System.out.print("Nome do Cliente: ");
                    String nomeCliente = sc.nextLine();
                    System.out.print("ID do Cliente: ");
                    String idCliente = sc.nextLine();

                    Cliente cliente = new Cliente(nomeCliente, idCliente);
                    Veiculo veiculo = new Veiculo(placa, placa);

                    cliente.addVeiculo(veiculo);
                    estacionamento.addVeiculo(veiculo, cliente);

                    System.out.println("Veículo registrado no estacionamento.");
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Placa do Veículo que está saindo: ");
                    String placaSaida = sc.nextLine();
                    double valorPago = estacionamento.saidaVeiculo(placaSaida);

                    if (valorPago >= 0) {
                        System.out.println("Veículo saiu. Valor a ser pago: $" + valorPago);
                    } else {
                        System.out.println("Veículo não encontrado no estacionamento.");
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("ID do Cliente para consulta: ");
                    String idConsulta = sc.nextLine();
                    Cliente clienteConsultado = estacionamento.consultarCliente(idConsulta);

                    if (clienteConsultado != null) {
                        System.out.println("Cliente: " + clienteConsultado.getNome());
                        System.out.println("Total de Veículos: " + clienteConsultado.getTotalVeiculos());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Arrecadação Total do Estacionamento: $" + estacionamento.getArrecadacaoTotal());
                    break;

                case 5:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        sc.close();
    }
}
