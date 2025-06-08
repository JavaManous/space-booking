package view;

import java.util.Scanner;
import controller.EquipamentoController;

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
        equipamentoController.setInput(input);
        boolean menu = true;
        while (menu) {
            mostrarMenu();
            int opcao = input.nextInt();
            input.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        equipamentoController.criar();
                        break;
                    case 2:
                        equipamentoController.listar();
                        break;
                    case 3:
                        equipamentoController.buscar();
                        break;
                    case 4:
                        equipamentoController.editar();
                        break;
                    case 5:
                        equipamentoController.deletar();
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
}