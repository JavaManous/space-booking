package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;
import utils.Log;

public abstract class ReservaDAO {

    private static final String CAMINHO = "./dados/reservas.ser";

    public static void salvarReservas(List<Reserva> reservas) throws IOException {
        File arquivo = new File(CAMINHO);
        arquivo.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(reservas);
        }

        System.out.println("Reservas salvas com sucesso. Total: " + reservas.size());
    }

    @SuppressWarnings("unchecked")
    public static List<Reserva> listarReservas() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Reserva>) ois.readObject();
        }
    }

    public static void deletarReserva(int id) throws Exception {
        List<Reserva> reservas = listarReservas();
        boolean removido = reservas.removeIf(r -> r.getId() == id);
        if (removido) {
            salvarReservas(reservas);
        } else {
            Log.setError("Reserva com ID " + id + " não encontrada para exclusão.");
            throw new Exception("Reserva não encontrada.");
        }
    }
}