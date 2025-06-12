package view;

import java.util.Scanner;

public class MenuGeral {
    private Scanner input;
    private SalaView salaView;
    private ClienteView clienteView;
    private EquipamentoView equipamentoView;

    public MenuGeral(Scanner input, SalaView salaView, ClienteView clienteView, EquipamentoView equipamentoView) {
        this.input = input;
        this.salaView = salaView;
        this.clienteView = clienteView;
        this.iniciarMenu();
    }

    private void limparMenu() {
        System.out.print("\033c");
    }

    private void mostrarMenu() {
        System.out.println("-- Menu Geral --");
        System.out.println("1. Reservas");
        System.out.println("2. Salas");
        System.out.println("3. Clientes");
        System.out.println("4. Equipamentos");
        System.out.println("5. Sair");
        System.out.println("\nEscolha uma opção:");
    }

    public void iniciarMenu() {

        int opcao = 0;
        boolean menu = true;

        while (menu) {
            this.limparMenu();
            this.mostrarMenu();

            opcao = input.nextInt();
            input.nextLine(); // <-- Consome o '\n' deixado pelo nextInt()

            switch (opcao) {
                case 1:
                    // Chamar o menu de reservas
                    break;
                case 2:
                    // Chamar o menu de salas
                    salaView.inicarMenu(input);
                    input.nextLine();
                    break;
                case 3:
                    clienteView.iniciarMenu(input);
                    input.nextLine();
                    break;
                case 4:
                    equipamentoView.iniciarMenu(input);
                    input.nextLine();
                    break;
                case 5:
                    menu = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    this.limparMenu();
                    input.nextLine();

                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        System.out.println("Obrigado por usar o sistema!");
    }

}