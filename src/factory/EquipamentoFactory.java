package factory;

import model.Equipamento;

public abstract class EquipamentoFactory {
    public static Equipamento criarEquipamento(String nome, String tipo, int id, int quantidade, float preco) {
        return new Equipamento(nome, tipo, id, quantidade, preco);
    }
}
