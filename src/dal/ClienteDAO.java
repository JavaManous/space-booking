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

    private static final String CAMINHO = "./dados/clientes.ser";

    public static void salvarCliente(List<Cliente> clientes) throws IOException {
        File arquivo = new File(CAMINHO);
        arquivo.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(clientes);
        }

        System.out.println("Clientes salvos com sucesso. Total: " + clientes.size());
    }

    @SuppressWarnings("unchecked")
    public static List<Cliente> listarClientes() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Cliente>) ois.readObject();
        }
    }

    public static void deletarCliente(int id) throws Exception {
        List<Cliente> clientes = listarClientes();
        boolean removido = clientes.removeIf(c -> c.getId() == id);
        if (removido) {
            salvarCliente(clientes);
        } else {
            Log.setError("Cliente com ID " + id + " não encontrado para exclusão.");
            throw new Exception("Cliente não encontrado.");
        }
    }
}
