package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import utils.Log;

public abstract class ClienteDAO {

    private static final String CAMINHO = "./dados";

    public static void salvarCliente(Cliente cliente) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = listarClientes();
            clientes.add(cliente);
        } catch (Exception e) {
            Log.setError("Erro ao listar clientes: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/cliente.ser"))){
            oos.writeObject(clientes);
        }

        System.err.println("Salvando cliente: " + cliente.toString());
    }

    public static Cliente buscarCliente(int id) {
        try {
            List<Cliente> clientes = listarClientes();
            for (Cliente c : clientes) {
                if (c.getId() == id) {
                    return c;
                }
            }
        } catch (Exception e) {
            Log.setError("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<Cliente> listarClientes() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/clientes.ser");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(arquivo);
            ObjectInputStream in = new ObjectInputStream(fileIn)) {

                return (List<Cliente>) in.readObject();
            }
    }

    public static int gerarNovoId() throws IOException, ClassNotFoundException {
        List<Cliente> clientes = listarClientes();
        if (clientes.isEmpty()) return 1;
        return clientes.stream().mapToInt(Cliente::getId).max().getAsInt() + 1;
    }

    public static void editarCliente(int id, Cliente cliente) throws IOException, ClassNotFoundException {
        List<Cliente> clientes = listarClientes();
        boolean encontrado = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, cliente);
                encontrado = true;
                break;
            }
        }
    if (encontrado) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/clientes.ser"))) {
            oos.writeObject(clientes);
        }
    } else {
        Log.setError("Cliente não encontrado para edição. ID: " + id);
    }
}

    public static void deletarCliente(int id) throws IOException, ClassNotFoundException {
        List<Cliente> clientes = listarClientes();
        boolean removido = clientes.removeIf(c -> c.getId() == id);
        if (removido) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/clientes.ser"))) {
                oos.writeObject(clientes);
            }
        } else {
            Log.setError("Cliente não encontrado para fazer exclusão. ID: " + id);
        }
    }
}