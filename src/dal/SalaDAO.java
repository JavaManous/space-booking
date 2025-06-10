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
    private static final String ARQUIVO = "/sala.ser";

    public static void salvarSala(Sala sala) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + ARQUIVO, true))) {
            oos.writeObject(sala);
        }

        System.err.println("Salvando sala: " + sala.toString());
    }

    public static Sala buscarSala(int numeroSala) {

        try {
            List<Sala> salas = listarSalas();
            Sala sala = null;
            for (Sala t : salas) {
                if (t.getNumeroSala() == numeroSala) {
                    sala = t;
                    break;
                }
            }
            return sala;
        } catch (Exception erro) {
            Log.setError("Erro ao buscar sala: \n" + erro.getMessage() + "\n");
        }

        return null;
    }

    public static List<Sala> listarSalas() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(arquivo);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {

            List<Sala> salasLidas = (List<Sala>) in.readObject();

            return salasLidas;
        } catch (Exception erro) {
            Log.setError(erro.getMessage());
        }

        return null;
    }

    public static void editarSala(int numeroSala, Sala salaAtualizada) throws IOException {

        try {
            List<Sala> salas = listarSalas();
            for (int i = 0; i < salas.size(); i++) {
                if (salas.get(i).getNumeroSala() == salaAtualizada.getNumeroSala()) {
                    salas.set(i, salaAtualizada);
                    break;
                }
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + ARQUIVO))) {
                oos.writeObject(salas);
            }
        } catch (Exception erro) {
            Log.setError(erro.getMessage());
        }

    }

    public static void deletarSala(int numeroSala) throws IOException, ClassNotFoundException {
        List<Sala> salas = listarSalas();
        salas.removeIf(sala -> sala.getNumeroSala() == numeroSala);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + ARQUIVO))) {
            oos.writeObject(salas);
        }
    }
}
