package factory;

import model.Sala;
// import model.Equipamento;

public abstract class SalaFactory {
    public static Sala criarSala(int numeroSala, int capacidadePessoas, String[] equipamentos) {
        return new Sala(numeroSala, capacidadePessoas, equipamentos);
    }
}
