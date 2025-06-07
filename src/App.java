import java.util.Scanner;

import controller.ClienteController;
import controller.SalaController;
import view.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Olá usuário, ?!");

        SalaController salaController = new SalaController();
        SalaView salaView = new SalaView(salaController);

        ClienteController clienteController = new ClienteController();
        ClienteView clienteView = new ClienteView(clienteController);

        Scanner input = new Scanner(System.in);
        MenuGeral menu = new MenuGeral(input, salaView, clienteView);
        menu.iniciarMenu();
    }
}
