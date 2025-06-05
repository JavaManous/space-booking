package controller;

import dal.ClienteDAO;
import factory.ClienteFactory;
import model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean cadastrarCliente(String nome, String cpf, String telefone, String email, String endereco) {
        if (nome == null || nome.trim().isEmpty() || 
            cpf == null || cpf.trim().isEmpty() ||
            telefone == null || telefone.trim().isEmpty()) {
                return false;
        }

        Cliente cliente = ClienteFactory.criarCliente(0, nome, cpf, telefone, email, endereco);

        if (!cliente.validarDocumento()) {
            return false;
        }

        return clienteDAO.salvar(cliente);
    }
}
