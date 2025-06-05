package dal;

import java.util.List;

import model.Cliente;

public class ClienteDAO {

    private static final String FILE_NAME = "clientes.ser";

    public static void salvarCliente(Cliente cliente) {
        // throw new UnsupportedOperationException("Método não implementado");
        System.err.println("Salvando cliente: " + cliente.toString());
    }

    public static Cliente buscarCliente(int id) {
        // throw new UnsupportedOperationException("Método não implementado");
        return null;
    }

    public static List<Cliente> listarClientes() {
        // throw new UnsupportedOperationException("Método não implementado");
        return null;
    }

    public static void editarCliente(int id, Cliente cliente) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public static void deletarCliente(int id) {
        throw new UnsupportedOperationException("Método não implementado");
    }
}