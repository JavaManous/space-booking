package view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.ReservaController;
import controller.ClienteController;
import controller.SalaController;
import model.Reserva;
import model.Cliente;
import model.Sala;
import model.Menu;

public class ReservaView extends Menu {

    private ReservaController reservaController;
    private ClienteController clienteController;
    private SalaController salaController;

    public ReservaView(ReservaController reservaController, ClienteController clienteController, SalaController salaController) {
        this.reservaController = reservaController;
        this.clienteController = clienteController;
        this.salaController = salaController;
    }

    public void mostrarMenu() {
        System.out.println("-- Menu de Reservas --");
        System.out.println("1. Criar Reserva");
        System.out.println("2. Buscar Reserva");
        System.out.println("3. Listar Reservas");
        System.out.println("4. Editar Reserva");
        System.out.println("5. Deletar Reserva");
        System.out.println("6. Voltar ao Menu Geral");
        System.out.println("\nEscolha uma opção: ");
    }

    public void iniciarMenu(Scanner input) {
        boolean menu = true;
        while (menu) {
            mostrarMenu();
            int opcao = input.nextInt();
            input.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o ID do cliente: ");
                        int idCliente = input.nextInt();
                        System.out.println("Digite o ID da sala: ");
                        int idSala = input.nextInt();
                        input.nextLine();
                        System.out.println("Digite a data de início (timestamp em ms): ");
                        long inicioMs = input.nextLong();
                        System.out.println("Digite a data de fim (timestamp em ms): ");
                        long fimMs = input.nextLong();
                        System.out.println("Digite o valor da hora: ");
                        float valorHora = input.nextFloat();
                        input.nextLine();
                        System.out.println("Digite o status (PENDENTE, CONFIRMADA, CANCELADA, CONCLUIDA): ");
                        String statusStr = input.next().toUpperCase();
                        Reserva.StatusReserva status = Reserva.StatusReserva.valueOf(statusStr);

                        Cliente cliente = clienteController.buscar(idCliente);
                        Sala sala = salaController.buscar(idSala);

                        Date dataInicio = new Date(inicioMs);
                        Date dataFim = new Date(fimMs);

                        Reserva reserva = reservaController.criar(
                            idCliente, cliente, sala, dataInicio, dataFim, valorHora, status
                        );
                        System.out.println("Reserva criada com sucesso!");
                        System.out.println("ID da reserva: " + reserva.getId());
                        System.out.println(reserva);
                        break;
                    case 2:
                        System.out.println("Digite o ID da reserva que deseja buscar: ");
                        int idBusca = input.nextInt();
                        Reserva buscada = reservaController.buscar(idBusca);
                        System.out.println("Detalhes da Reserva:");
                        System.out.println(buscada);
                        break;
                    case 3:
                        List<Reserva> reservas = reservaController.listarReservas();
                        if (reservas.isEmpty()) {
                            System.out.println("Nenhuma reserva encontrada.");
                        } else {
                            reservas.forEach(System.out::println);
                        }
                        break;
                    case 4:
                        System.out.println("Digite o ID da reserva que deseja editar: ");
                        int idEdit = input.nextInt();
                        System.out.println("Digite o novo ID do cliente: ");
                        int idClienteEdit = input.nextInt();
                        System.out.println("Digite o novo ID da sala: ");
                        int idSalaEdit = input.nextInt();
                        input.nextLine();
                        System.out.println("Digite a nova data de início (timestamp em ms): ");
                        long inicioMsEdit = input.nextLong();
                        System.out.println("Digite a nova data de fim (timestamp em ms): ");
                        long fimMsEdit = input.nextLong();
                        System.out.println("Digite o novo valor da hora: ");
                        float valorHoraEdit = input.nextFloat();
                        input.nextLine();
                        System.out.println("Digite o novo status (PENDENTE, CONFIRMADA, CANCELADA, CONCLUIDA): ");
                        String statusEditStr = input.next().toUpperCase();
                        Reserva.StatusReserva statusEdit = Reserva.StatusReserva.valueOf(statusEditStr);

                        Cliente clienteEdit = clienteController.buscar(idClienteEdit);
                        Sala salaEdit = salaController.buscar(idSalaEdit);

                        Date dataInicioEdit = new Date(inicioMsEdit);
                        Date dataFimEdit = new Date(fimMsEdit);

                        Reserva editada = reservaController.editar(
                            idEdit, clienteEdit, salaEdit, dataInicioEdit, dataFimEdit, valorHoraEdit, statusEdit
                        );
                        System.out.println("Reserva editada com sucesso!");
                        System.out.println(editada);
                        break;
                    case 5:
                        System.out.println("Digite o ID da reserva que deseja deletar: ");
                        int idDel = input.nextInt();
                        reservaController.deletar(idDel);
                        System.out.println("Reserva deletada com sucesso!");
                        break;
                    case 6:
                        menu = false;
                        System.out.println("Voltando ao menu geral...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}