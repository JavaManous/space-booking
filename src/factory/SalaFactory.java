package factory;

import model.Sala;
import java.util.List;

import model.Equipamento;

public abstract class SalaFactory {
    public static Sala criarSala(int numeroSala, int capacidadePessoas, List<Equipamento> equipamentos) {
        return new Sala(numeroSala, capacidadePessoas, equipamentos);
    }
}
