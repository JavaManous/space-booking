package view;

import java.util.List;
import java.util.Scanner;

import controller.ClienteController;
import model.Cliente;
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
        boolean menu = true;
        while (menu) {
            mostrarMenu();
            int opcao = input.nextInt();
            input.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o nome do cliente: ");
                        String nome = input.nextLine();
                        System.out.println("Digite o CPF do cliente: ");
                        String cpf = input.next();
                        System.out.println("Digite o telefone do cliente: ");
                        String telefone = input.next();
                        System.out.println("Digite o email do cliente: ");
                        String email = input.next();
                        System.out.println("Digite o endereço do cliente: ");
                        input.nextLine();
                        String endereco = input.nextLine();
                        Cliente cliente = clienteController.criar(nome, email, endereco, cpf, telefone);
                        System.out.println("Cliente cadastrado com sucesso!");
                        System.out.println("Seu ID é: " + cliente.getId());
                        System.out.println(cliente);
                        break;
                    case 2:
                        System.out.println("Digite o id que deseja buscar: ");
                        int idBusca = input.nextInt();
                        Cliente buscado = clienteController.buscar(idBusca);
                        System.out.println("Detalhes do Cliente: ");
                        System.out.println(buscado);
                        break;
                    case 3:
                        List<Cliente> clientes = clienteController.listarClientes();
                        if (clientes.isEmpty()) {
                            System.out.println("Nenhum cliente encontrado.");
                        } else {
                            clientes.forEach(System.out::println);
                        }
                        break;
                    case 4:
                        System.out.println("Digite o ID do cliente que deseja editar: ");
                        int idEdit = input.nextInt();
                        input.nextLine();
                        System.out.println("Digite o novo nome do cliente: ");
                        String nomeEdit = input.nextLine();
                        System.out.println("Digite o novo CPF do cliente: ");
                        String cpfEdit = input.next();
                        System.out.println("Digite o novo telefone do cliente: ");
                        String telefoneEdit = input.next();
                        System.out.println("Digite o novo email do cliente: ");
                        String emailEdit = input.next();
                        System.out.println("Digite o novo endereço do cliente: ");
                        input.nextLine();
                        String enderecoEdit = input.nextLine();
                        Cliente editado = clienteController.editar(idEdit, nomeEdit, cpfEdit, telefoneEdit, emailEdit, enderecoEdit);
                        System.out.println("Cliente editado com sucesso!");
                        System.out.println(editado);
                        break;
                    case 5:
                        System.out.println("Digite o ID do cliente que deseja deletar: ");
                        int idDel = input.nextInt();
                        clienteController.deletar(idDel);
                        System.out.println("Cliente deletado com sucesso!");
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
