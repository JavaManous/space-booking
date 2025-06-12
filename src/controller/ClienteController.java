package controller;

import java.util.ArrayList;
import java.util.List;

import dal.ClienteDAO;
import factory.ClienteFactory;
import model.Cliente;
import utils.Log;

public class ClienteController {

    private List<Cliente> clientes;

    public ClienteController() {
        try {
            this.clientes = ClienteDAO.listarClientes();
        } catch (Exception e) {
            this.clientes = new ArrayList<>();
        }
    }

    public Cliente criar(String nome, String email, String endereco, String cpf, String telefone) throws Exception {
        if (!validarDocumento(cpf)) {
            Log.setError("CPF inválido ao tentar cadastrar cliente: " + cpf);
            throw new Exception("CPF inválido!");
        }
        int novoId = gerarNovoId();
        Cliente cliente = ClienteFactory.criarCliente(novoId, nome, email, endereco, cpf, telefone);
        clientes.add(cliente);
        ClienteDAO.salvarCliente(clientes);
        return cliente;
    }

    public Cliente buscar(int id) throws Exception {
        for (Cliente c : clientes) {
            if (c.getId() == id) return c;
        }
        Log.setError("Cliente não encontrado: " + id);
        throw new Exception("Cliente não encontrado: " + id);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public Cliente editar(int id, String nome, String cpf, String telefone, String email, String endereco) throws Exception {
        if (!validarDocumento(cpf)) {
            Log.setError("CPF inválido ao tentar editar cliente: " + cpf);
            throw new Exception("CPF inválido!");
        }
        Cliente clienteAtualizado = ClienteFactory.criarCliente(id, nome, email, endereco, cpf, telefone);
        boolean encontrado = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, clienteAtualizado);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            ClienteDAO.salvarCliente(clientes);
            return clienteAtualizado;
        } else {
            Log.setError("Cliente não encontrado para edição. ID: " + id);
            throw new Exception("Cliente não encontrado para edição. ID: " + id);
        }
    }

    public void deletar(int id) throws Exception {
        boolean removido = clientes.removeIf(c -> c.getId() == id);
        if (removido) {
            ClienteDAO.salvarCliente(clientes);
        } else {
            Log.setError("Cliente não encontrado para exclusão. ID: " + id);
            throw new Exception("Cliente não encontrado: " + id);
        }
    }

    public int gerarNovoId() {
        if (clientes.isEmpty()) return 1;
        return clientes.stream().mapToInt(Cliente::getId).max().getAsInt() + 1;
    }

    private static boolean validarDocumento(String cpf) {
        if (cpf == null || cpf.isEmpty()) return false;
        if (cpf.length() != 11) return false;
        if (!cpf.matches("\\d{11}")) return false;

        boolean todosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        return !todosIguais;
    }
}
