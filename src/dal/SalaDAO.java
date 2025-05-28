package dal;

import java.util.List;

import model.Sala;

public abstract class SalaDAO {
    public static void salvarSala(Sala sala) {
        // throw new UnsupportedOperationException("Método não implementado");
        System.err.println("Salvando sala: " + sala.toString());
    }

    public static Sala buscarSala(int numeroSala) {
        // throw new UnsupportedOperationException("Método não implementado");
        return null;
    }

    public static List<Sala> listarSalas() {
        // throw new UnsupportedOperationException("Método não implementado");
        return null;
    }

    public static void editarSala(int numeroSala, Sala sala) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public static void deletarSala(int numeroSala) {
        throw new UnsupportedOperationException("Método não implementado");
    }
}
