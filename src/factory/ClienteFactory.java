package factory;

import java.util.Date;

import model.Cliente;

public class ClienteFactory {
    public static Cliente criarCliente(int id, String nome, String cpf, String telefone, String email, String endereco) {
        return new Cliente(id, nome, cpf, telefone, email, endereco, new Date());
    }
}
