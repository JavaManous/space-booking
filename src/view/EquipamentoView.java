package view;

import java.util.List;
import java.util.Scanner;
import controller.EquipamentoController;
import model.Equipamento;

public class EquipamentoView {

    private EquipamentoController equipamentoController;

    public EquipamentoView(EquipamentoController equipamentoController) {
        this.equipamentoController = equipamentoController;
    }

    public void mostrarMenu() {
        System.out.println("\n--- Menu Equipamentos ---");
        System.out.println("1. Cadastrar equipamento");
        System.out.println("2. Listar equipamentos");
        System.out.println("3. Buscar equipamento");
        System.out.println("4. Editar equipamento");
        System.out.println("5. Deletar equipamento");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
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
                        cadastrarEquipamento(input);
                        break;
                    case 2:
                        listarEquipamentos();
                        break;
                    case 3:
                        buscarEquipamento(input);
                        break;
                    case 4:
                        editarEquipamento(input);
                        break;
                    case 5:
                        deletarEquipamento(input);
                        break;
                    case 0:
                        menu = false;
                        System.out.println("Saindo do menu de equipamentos.");
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

    private void cadastrarEquipamento(Scanner input) {
        System.out.print("Digite o nome do equipamento: ");
        String nome = input.nextLine();

        System.out.print("Digite o tipo do equipamento: ");
        String tipo = input.nextLine();

        System.out.print("Digite o id do equipamento: ");
        int id = input.nextInt();

        System.out.print("Digite a quantidade: ");
        int quantidade = input.nextInt();

        System.out.print("Digite o preço: ");
        float preco = input.nextFloat();
        input.nextLine();

        try {
            equipamentoController.criar(nome, tipo, id, quantidade, preco);
            System.out.println("Equipamento cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar equipamento.");
        }
    }

    private void buscarEquipamento(Scanner input) {
        System.out.print("Digite o ID do equipamento que deseja buscar: ");
        int id = input.nextInt();
        input.nextLine();

        try {
            Equipamento equipamento = equipamentoController.buscar(id);
            if (equipamento != null) {
                System.out.println("Detalhes do Equipamento:");
                System.out.println(equipamento);
            } else {
                System.out.println("Equipamento não encontrado: " + id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar equipamento.");
        }
    }

    private void listarEquipamentos() {
        try {
            List<Equipamento> equipamentos = equipamentoController.listar();
            if (equipamentos == null || equipamentos.isEmpty()) {
                System.out.println("Nenhum equipamento encontrado.");
                return;
            }
            System.out.println("Lista de Equipamentos:");
            for (Equipamento equipamento : equipamentos) {
                System.out.println(equipamento);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar equipamentos.");
        }
    }

    private void editarEquipamento(Scanner input) {
        System.out.print("Digite o ID do equipamento que deseja editar: ");
        int id = input.nextInt();
        input.nextLine();

        try {
            Equipamento equipamentoExistente = equipamentoController.buscar(id);
            if (equipamentoExistente == null) {
                System.out.println("Equipamento não encontrado.");
                return;
            }

            System.out.print("Digite o novo nome do equipamento: ");
            String nome = input.nextLine();

            System.out.print("Digite o novo tipo do equipamento: ");
            String tipo = input.nextLine();

            System.out.print("Digite a nova quantidade: ");
            int quantidade = input.nextInt();

            System.out.print("Digite o novo preço: ");
            float preco = input.nextFloat();
            input.nextLine();

            equipamentoController.editar(id, nome, tipo, quantidade, preco);
            System.out.println("Equipamento editado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao editar equipamento.");
        }
    }

    private void deletarEquipamento(Scanner input) {
        System.out.print("Digite o ID do equipamento que deseja deletar: ");
        int id = input.nextInt();
        input.nextLine();

        try {
            equipamentoController.deletar(id);
            System.out.println("Equipamento deletado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao deletar equipamento.");
        }
    }
}