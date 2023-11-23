package application;
import java.util.Scanner;
import entities.*;
import entities.Enums.ECliente;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento("Estacionamento", 3, 5);
        MenuHandler menuHandler = new MenuHandler(sc, estacionamento);
        menuHandler.runMenu();
        sc.close();
    }
}

class MenuHandler {
    private Scanner scanner;
    private Estacionamento estacionamento;

    public MenuHandler(Scanner scanner, Estacionamento estacionamento) {
        this.scanner = scanner;
        this.estacionamento = estacionamento;
    }

    public void runMenu() {
        boolean sair = false;

        while (!sair) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    registrarVeiculo();
                    break;
                case 3:
                    estacionarCarro();
                    break;
                case 4:
                    calcularValorSaida();
                    break;
                case 5:
                    exibirTotalArrecadado();
                    break;
                case 6:
                    totalArrecadadoMes();
                    break;
                case 7:
                    exibirGastosMedios();
                    break;
                case 8:
                    totalArrecadadoMesClienteHorista();
                    break;
                case 9:
                    exibirTotalArrecadadoClienteHorista();
                    break;
                case 10:
                    sair = true;
                    break;
                default:
                    System.out.println("Escolha incorreta. Tente novamente.");
                    break;
            }
        }
    }

    private void exibirMenu() {
        System.out.println("===== MENU DO ESTACIONAMENTO =====");
        System.out.println("1. Registrar Cliente");
        System.out.println("2. Registrar Veiculo");
        System.out.println("3. Estacionar carro");
        System.out.println("4. Calcular valor de saida");
        System.out.println("5. Total arrecadado do estacionamento");
        System.out.println("6. Total arrecadado do estacionamento em um mes");
        System.out.println("7. Media de gasto do publico");
        System.out.println("8. Total arrecadado do cliente horista ");
        System.out.println("9. Total arrecadado no Mes cliente horista; ");
        System.out.println("10. Sair");
        System.out.print("Escolha uma opção: ");
    }

private void registrarCliente() {
    System.out.println("===== REGISTRO DE CLIENTE =====");
    System.out.print("Nome do cliente: ");
    String nome = scanner.nextLine();
    System.out.print("ID do cliente: ");
    String id = scanner.nextLine();
    System.out.println("Selecione o tipo de cliente:");
    for (ECliente tipo : ECliente.values()) {
        System.out.println((tipo.ordinal() + 1) + ". " + tipo.getNome());
    }
    int escolhaTipo = scanner.nextInt();
    scanner.nextLine();
    ECliente tipoCliente = null;
    if (escolhaTipo > 0 && escolhaTipo <= ECliente.values().length) {
        tipoCliente = ECliente.values()[escolhaTipo - 1];
    } 

    Cliente cliente = new Cliente(nome, id, tipoCliente);
    estacionamento.addCliente(cliente);
}

    private void registrarVeiculo() {
        System.out.println("===== REGISTRO DE VEICULO =====");
        System.out.print("Placa do Veiculo: ");
        String placa = scanner.nextLine();
        Veiculo veiculo = new Veiculo(placa);
        System.out.print("ID DO CLIENTE: ");
        String idCliente = scanner.nextLine();
        estacionamento.addVeiculo(veiculo, idCliente);
    }

    private void estacionarCarro() {
        System.out.println("===== ESTACIONANDO VEICULO =====");
        System.out.print("Placa do carro: ");
        String placaVeiculo = scanner.nextLine();
        estacionamento.estacionar(placaVeiculo);
        System.out.println("Veiculo estacionado com sucesso!");
    }

    private void calcularValorSaida() {
        System.out.println("===== CALCULANDO SAIDA VEICULO =====");
        System.out.println("Digite a placa do carro: ");
        String placaVaga = scanner.nextLine();
        System.out.print("Valor a ser pago: " + estacionamento.sair(placaVaga));
        System.out.println();
    }

    private void exibirTotalArrecadado() {
        System.out.println("===== TOTAL ARRECADADO DO ESTACIONAMENTO =====");
        double totalArrecadado = estacionamento.totalArrecadado();
        System.out.println("Total arrecadado: " + totalArrecadado);
    }

    private void totalArrecadadoMes() {
        System.out.println("===== TOTAL ARRECADADO DO ESTACIONAMENTO EM UM MES =====");
        System.out.println("Qual mes voce deseja? ");
        int mes = scanner.nextInt();
        System.out.print("Total arrecadado: " + estacionamento.arrecadacaoNoMes(mes));
    }

    private void exibirGastosMedios() {
        System.out.println("===== MEDIA DE GASTOS DO PUBLICO =====");
        System.out.println("Gastos medios: " + estacionamento.valorMedioPorUso());
    }

    private void totalArrecadadoMesClienteHorista() {
        System.out.println("===== TOTAL ARRECADADO DO ESTACIONAMENTO EM UM MES CLIENTE HORISTA =====");
        System.out.println("Qual mes voce deseja? ");
        int mes = scanner.nextInt();
        System.out.print("Total arrecadado: " + estacionamento.arrecadacaoNoMesClienteHorista(mes));
    }

    private void exibirTotalArrecadadoClienteHorista() {
        System.out.println("===== TOTAL ARRECADADO DO ESTACIONAMENTO =====");
        double totalArrecadadoClienteHorista = estacionamento.totalArrecadadoHorista();
        System.out.println("Total arrecadado: " + totalArrecadadoClienteHorista);
    }

    // Dudão, arruma isso. O método de exibir top 5 não está funcionando. Não pode
    // ter get tbm, se tiver get, tá errado.
    // Mas o caminho dele já ta certinho no main, é só arrumar, e tirar o comentário
    // q ele vai chamar certinho.
    // private void exibirTop5() {
    // System.out.println("===== TOP 5 CLIENTES DO ESTACIONAMENTO =====");
    // List<Cliente> top5 = estacionamento.top5();
    // for (Cliente cliente : top5) {
    // System.out.println(cliente.toString());
    // }
    // }
}
