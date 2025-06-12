package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.EquipamentoController;
import controller.SalaController;
import dal.EquipamentoDAO;
import model.Equipamento;
import model.Menu;;

public class SalaView extends Menu {

    private SalaController salaController;

    public SalaView(SalaController salaController) {
        this.salaController = salaController;
    }

    public void mostrarMenu() {
        System.out.println("-- Menu de Salas --");
        System.out.println("1. Criar Sala");
        System.out.println("2. Buscar Sala");
        System.out.println("3. Listar Salas");
        System.out.println("4. Editar Sala");
        System.out.println("5. Deletar Sala");
        System.out.println("6. Voltar ao Menu Geral");
        System.out.println("\nEscolha uma opção:");
    }

    public void inicarMenu(Scanner input) {
        int opcao = 0;
        boolean menu = true;

        do {
            mostrarMenu();

            opcao = input.nextInt();
            input.nextLine();
            try {
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o número da sala:");
                        int numeroSala = input.nextInt();

                        System.out.println("Digite a capacidade de pessoas da sala:");
                        int capacidade = input.nextInt();

                        System.out.println("Digite os IDs dos equipamentos (separados por vírgula):");

                        List<Integer> idsEquipamentos = new ArrayList<>();
                        List<Equipamento> equipamentosDisponiveis = EquipamentoController.listar();
                        if (equipamentosDisponiveis.isEmpty()) {
                            System.out.println("Nenhum equipamento cadastrado no sistema.");
                        } else {

                            for (Equipamento eq : equipamentosDisponiveis) {
                                System.out.println("ID: " + eq.getId() + " | Nome: " + eq.getNome());
                            }

                            String idStr = input.next();
                            String[] ids = idStr.split(",");

                            for (String id : ids) {
                                idsEquipamentos.add(Integer.parseInt(id.trim()));
                            }
                        }

                        salaController.criar(numeroSala, capacidade, idsEquipamentos);
                        break;
                    case 2:
                        salaController.buscar();
                        break;
                    case 3:
                        salaController.listar();
                        break;
                    case 4:
                        salaController.editar();
                        break;
                    case 5:
                        salaController.deletar();
                        break;
                    case 6:
                        menu = false;
                        System.out.println("Teste...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (Exception e) {

            }

        } while (menu);
    }
}
