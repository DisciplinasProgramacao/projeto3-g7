import java.util.Scanner;
import entities.*;

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
            System.out.println("4. Sair");
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
                    Veiculo veiculo = new Veiculo(placa);

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
                        System.out.println(clienteConsultado.historicoCliente());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
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



    // Gerar aleatoriamente um mínimo de 50 usos de vagas distribuídas para clientes
    // e estacionamentos;
    public void carregarDados() {
        
        Estacionamento estacionamento = new Estacionamento("Estacionamento1", 4, 5);
        Estacionamento estacionamento2 = new Estacionamento("Estacionamento2", 5, 6);
        Estacionamento estacionamento3 = new Estacionamento("Estacionamento3", 4, 5);

        Cliente cliente1 = new Cliente("Cliente1", "1");
        Cliente cliente2 = new Cliente("Cliente2", "2");
        Cliente cliente3 = new Cliente("Cliente3", "3");

        Veiculo veiculo1 = new Veiculo("1");
        Veiculo veiculo2 = new Veiculo("2");
        Veiculo veiculo3 = new Veiculo("3");

        estacionamento.addCliente(cliente3);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente1);

        cliente1.addVeiculo(veiculo1);
        cliente2.addVeiculo(veiculo2);
        cliente3.addVeiculo(veiculo3);

        estacionamento.addVeiculo(veiculo1, cliente1);
        estacionamento.addVeiculo(veiculo2, cliente2);
        estacionamento.addVeiculo(veiculo3, cliente3);

        for(int i = 0; i<50; i++){
            estacionamento.gerarVagas();
        }
    
}
}

