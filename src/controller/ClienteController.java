package controller;

import java.util.List;
import java.util.Scanner;

import dal.ClienteDAO;
import factory.ClienteFactory;
import model.Cliente;
import model.Controller;
import utils.Log;

public class ClienteController implements Controller {
    private Scanner input;

    public void setInput(Scanner input) {
        this.input = input;
    }

    public void criar() throws Exception {
        System.out.println("Digite o nome do cliente: ");
        String nome = input.nextLine();

        System.out.println("Digite o CPF do cliente: ");
        String cpf = input.next();

        System.out.println("Digite o telefone do cliente: ");
        String telefone = input.next();
        
        System.out.println("Digite o email do cliente: ");
        String email = input.next();

        System.out.println("Digite o endereço do cliente: ");
        String endereco = input.nextLine();

        int novoId = ClienteDAO.gerarNovoId();
        Cliente cliente = ClienteFactory.criarCliente(novoId, nome, email, endereco, cpf, telefone);

        if (!cliente.validarDocumento()) {
            System.out.println("CPF inválido!");
            return;
        }

        ClienteDAO.salvarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void buscar() throws Exception {
        System.out.println("Digie o id que deseja buscar: ");
        int id = input.nextInt();

        Cliente cliente = ClienteDAO.buscarCliente(id);
        if (cliente != null) {
            System.out.println("Detalhes do Cliente: ");
            System.out.println(cliente);
        } else {
            System.out.println("Clietne não encontrado: " + id);
        }
    }

    public void listar() throws Exception {
        try {
            List<Cliente> clientes = ClienteDAO.listarClientes();
            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
                return;
            }

            for (Cliente cliente : clientes) {
                System.out.println("Imprimindo clientes...");
                System.out.println(cliente.toString());
            }
        } catch (Exception e) {
            Log.setError("Erro ao listas clientes: \n" + e.getMessage() + "\n");
        }
    }

    public void editar() throws Exception {
        System.out.println("Digite o ID do cliente que deseja editar: ");
        int id = input.nextInt();
        input.nextLine();

        Cliente clienteExistente = ClienteDAO.buscarCliente(id);
        if (clienteExistente == null) {
            System.out.println("Cliente não encontrado: " + id);
            return;
        }

        System.out.println("Digite o novo nome do cliente: ");
        String nome = input.nextLine();

        System.out.println("Digite o novo CPF do cliente: ");
        String cpf = input.next();

        System.out.println("Digite o novo telefone do cliente: ");
        String telefone = input.next();

        System.out.println("Digite o novo email do cliente: ");
        String email = input.next();

        System.out.println("Digite o novo endereço do cliente: ");
        input.nextLine();
        String endereco = input.nextLine();

        Cliente clienteAtualizado = ClienteFactory.criarCliente(id, nome, cpf, telefone, email, endereco);

        if (!clienteAtualizado.validarDocumento()) {
            System.out.println("CPF inválido!");
            return;
        }

        ClienteDAO.editarCliente(id, clienteAtualizado);
        System.out.println("Cliente editado com sucesso!");
    }

    public void deletar() throws Exception {
        System.out.println("Digite o ID do cliente que deseja deletar: ");
        int id = input.nextInt();

        Cliente cliente = ClienteDAO.buscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado: " + id);
            return;
        }

        ClienteDAO.deletarCliente(id);
        System.out.println("Cliente deletado com sucesso!");
    }
}
