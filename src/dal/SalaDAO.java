package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Sala;
import utils.Log;

public abstract class SalaDAO {
    private static final String CAMINHO = "./dados";

    public static void salvarSala(Sala sala) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        List<Sala> salas = new ArrayList<Sala>();
        try {
            salas = listarSalas();
            salas.add(sala);
        } catch (Exception e) {
            System.err.println("Erro ao listar salas: \n" + e.getMessage() + "\n");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/sala.ser", true))) {
            oos.writeObject(salas);
        }

        System.err.println("Salvando sala: " + sala.toString());
    }

    public static Sala buscarSala(int numeroSala) {
        // throw new UnsupportedOperationException("Método não implementado");

        Log.setError("tESTE AJSDFKANMSFENMAEWGMNAGE");
        return null;
    }

    public static List<Sala> listarSalas() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/salas.ser");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(arquivo);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {

            List<Sala> objetoLido = (List<Sala>) in.readObject();
            System.out.println("Objeto lido: " + objetoLido);

        }
        return new ArrayList<Sala>();

    }

    public static void editarSala(int numeroSala, Sala sala) throws IOException {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public static void deletarSala(int numeroSala) throws IOException {
        throw new UnsupportedOperationException("Método não implementado");
    }
}
