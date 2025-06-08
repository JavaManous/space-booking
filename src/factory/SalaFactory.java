package factory;

import model.Sala;
import java.util.ArrayList;
import model.Equipamento;

public abstract class SalaFactory {
    public static Sala criarSala(int numeroSala, int capacidadePessoas, ArrayList<Equipamento> equipamentos) {
        return new Sala(numeroSala, capacidadePessoas, equipamentos);
    }
}
