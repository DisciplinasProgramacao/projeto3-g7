import java.util.Scanner;
import entities.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        Estacionamento estacionamento = new Estacionamento("Estacionamento", 3,5);
        boolean sair = false;


        while (!sair) {
            System.out.println("===== MENU DO ESTACIONAMENTO =====");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Registrar Veiculo");
            System.out.println("3. Estacionar carro");
            System.out.println("4. Calcular valor de saida");
            System.out.println("5. Total arrecadado do estacionamento");
            System.out.println("6. Total arrecadado do estacionamento em um mes");
            System.out.println("7. Media de gasto do publico");
            System.out.println("8. Top 5 clientes do estacionamento ");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("REGISTRAR CLIENTE");
                    System.out.print("Nome do cliente?: ");
                    sc.nextLine();
                    String nome = sc.nextLine();
                    System.out.print("ID do cliente: ");
                    String id = sc.nextLine();
                    Cliente cliente = new Cliente(nome, id);
                    estacionamento.addCliente(cliente);
                    break;
                case 2:
                    System.out.print("Placa do Veiculo: ");
                    String placa = sc.nextLine();
                    sc.nextLine();
                    Veiculo veiculo = new Veiculo(placa);
                    System.out.print("ID DO CLIENTE: ");
                    String idCliente = sc.nextLine();
                    estacionamento.addVeiculo(veiculo, idCliente);
                    break;
                case 3:
                    System.out.print("Placa do carro: ");
                    String placaVeiculo = sc.nextLine();
                    sc.nextLine();
                    estacionamento.estacionar(placaVeiculo);
                    break;
                case 4:
                    System.out.println("Digite a placa do carro:");
                    String placaVaga = sc.nextLine();
                    sc.nextLine();
                    System.out.print("Valor a ser pago: " + estacionamento.sair(placaVaga));
                    break;
                case 5:
                    System.out.println("Total arrecadado: " + estacionamento.totalArrecadado());
                    break;
                case 6:
                    System.out.println("Qual mes voce deseja?: ");
                    int mes = sc.nextInt();
                    System.out.print("Total arrecadado: " + estacionamento.arrecadacaoNoMes(mes));
                    break;
                case 7:
                    System.out.println("Gastos medios: " + estacionamento.valorMedioPorUso());
                    break;
                case 9:
                    sair = false;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }

        sc.close();
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
        }
    }

    
    
    
}

