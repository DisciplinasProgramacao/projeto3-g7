import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Estacionamento;
import entities.Vaga;
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

    // Gerar aleatoriamente um mínimo de 50 usos de vagas distribuídas para clientes
    // e estacionamentos;

public static void carregarDados() throws IOException{
        BufferedReader bre = new BufferedReader(new FileReader("estacionamentos.txt"));
        String linhae = "";
        
        while((linhae = bre.readLine()) != null){
            String[] linhas = linhae.split(";", 0);
            Estacionamento e = new Estacionamento(linhas[0], Integer.parseInt(linhas[1]), Integer.parseInt(linhas[2]));
             estacionamentos.add(e);	
        }

        BufferedReader brv = new BufferedReader(new FileReader("veiculos.txt"));
        String linhav = "";
        
        while((linhav = brv.readLine()) != null){
            String[] linhas = linhav.split(";", 0);
            Veiculo v = new Veiculo(linhas[0]);
            veiculos.add(v);
        }

        BufferedReader brc = new BufferedReader(new FileReader("clientes.txt"));
        String linhac = "";
        
        while((linhac = brc.readLine()) != null){
            String[] linhas = linhac.split(";", 0);
            Cliente c = new Cliente(linhas[0], linhas[1]);
            clientes.add(c);
        }
    
}
}
