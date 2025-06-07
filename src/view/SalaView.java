package view;

import java.util.Scanner;

import controller.SalaController;
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

        salaController.setInput(input);

        do {
            mostrarMenu();

            opcao = input.nextInt();
            input.nextLine();
            try {
                switch (opcao) {
                    case 1:
                        salaController.criar();
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
                // TODO: handle exception
            }

        } while (menu);
    }
}
