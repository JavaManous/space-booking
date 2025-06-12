package factory;

import java.util.Date;

import model.Cliente;

public class ClienteFactory {
    public static Cliente criarCliente(int id, String nome, String email, String endereco, String cpf, String telefone) {
        return new Cliente(id, nome, email, endereco, cpf, telefone, new Date());
    }

    public static Cliente criarCliente(String nome, String email, String endereco, String cpf, String telefone) {
            return new Cliente(nome, email, endereco, cpf, telefone);
        }

}
