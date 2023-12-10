package application;
import java.util.Scanner;
import entities.*;
import entities.Enums.ECliente;
import entities.Enums.ETurnos;
import entities.Enums.Servicos;
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
                    exibirTop5();
                    break;
                case 10:
                    exibirHistoricoCliente();
                    break;  
                case 11:
                    servicos();
                    break;  
                case 12:
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
        System.out.println("8. Total arrecadado no Mes cliente horista; ");
        System.out.println("9. Exibir Top 5");
        System.out.println("10. Exibir Historico do Cliente");
        System.out.println("11. Serviços");
        System.out.println("12. Sair");
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

        if (tipoCliente == ECliente.TURNO) {
            System.out.println("Selecione o turno do cliente:");
            for (ETurnos turno : ETurnos.values()) {
                System.out.println((turno.ordinal() + 1) + ". " + turno.getNome());
            }
            int escolhaTurno = scanner.nextInt();
            scanner.nextLine();
            ETurnos turnoCliente = null;
            if (escolhaTurno > 0 && escolhaTurno <= ETurnos.values().length) {
                turnoCliente = ETurnos.values()[escolhaTurno - 1];
            } else {
                System.out.println("Escolha inválida para o turno. O cliente será registrado sem turno.");
            }

            Cliente cliente = new Cliente(nome, id, tipoCliente, turnoCliente);
            estacionamento.addCliente(cliente);
        } else {
            Cliente cliente = new Cliente(nome, id, tipoCliente);
            estacionamento.addCliente(cliente);
        }
    }

     public static boolean validaEstacionamento( Estacionamento estacionamento ) {
        return estacionamento != null;
        
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
        System.out.println();
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
        System.out.println();
    }

    private void exibirTop5() {   
        System.out.println("===== TOP 5 CLIENTES =====");
        System.out.println(estacionamento.top5Clientes());
    }

    private void exibirHistoricoCliente() {
        System.out.println("===== HISTORICO DO CLIENTE =====");
        System.out.println("Qual o id do cliente?");
        String id = scanner.nextLine();
        System.out.println("Total arrecadado: " + estacionamento.historicoCliente(id));
    }
    
    private void servicos(){
        System.out.println("===== SERVICOS =====");
        System.out.println("Escolha o tipo de serviço:");
        for (Servicos tipoServico : Servicos.values()) {
            System.out.println((tipoServico.ordinal() + 1) + ". " + tipoServico.getNome() +
                    " - Preço: R$" + tipoServico.getValor());
        }
        int escolha = scanner.nextInt();
        scanner.nextLine();
        if (escolha > 0 && escolha <= Servicos.values().length) {
            Servicos tipoServicoEscolhido = Servicos.values()[escolha - 1];
            System.out.println("Você escolheu o serviço: " + tipoServicoEscolhido.getNome() +
                    " - Preço: R$" + tipoServicoEscolhido.getValor());
        } else {
            System.out.println("Escolha inválida.");
        }
    }
}