import java.util.Scanner;

import controller.ClienteController;
import view.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Olá usuário, bem vindo ao Space Booking!");

        SalaView salaView = new SalaView();

        ClienteController clienteController = new ClienteController();
        ClienteView clienteView = new ClienteView(clienteController);

        EquipamentoView equipamentoView = new EquipamentoView();

        Scanner input = new Scanner(System.in);
        MenuGeral menu = new MenuGeral(input, salaView, clienteView, equipamentoView);
        menu.iniciarMenu();
    }
}
