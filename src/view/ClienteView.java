package view;

import java.util.Scanner;

import controller.ClienteController;
import model.Menu;

public class ClienteView extends Menu {
    
    private ClienteController clienteController;

    public ClienteView(ClienteController clienteController) {
        this.clienteController = clienteController;
    }

    public void mostrarMenu() {
        System.out.println("-- Menu de Clientes --");
        System.out.println("1. Criar Cliente");
        System.out.println("2. Buscar Cliente");
        System.out.println("3. Listar Clientes");
        System.out.println("4. Editar Cliente");
        System.out.println("5. Deletar Cliente");
        System.out.println("6. Voltar ao Menu Geral");
        System.out.println("\nEscolha uma opção: ");
    }

    public void iniciarMenu(Scanner input) {
        int opcao = 0;
        boolean menu = true;

        clienteController.setInput(input);

        do {
            mostrarMenu();

            opcao = input.nextInt();
            input.nextLine();
            try {
                switch (opcao) {
                    case 1:
                        clienteController.criar();
                        break;
                    case 2:
                        clienteController.buscar();
                        break;
                    case 3:
                        clienteController.listar();
                        break;
                    case 4:
                        clienteController.editar();
                        break;
                    case 5:
                        clienteController.deletar();
                        break;
                    case 6:
                        menu = false;
                        System.out.println("Voltando ao meu geral...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        } while (menu);
        
    }
}
