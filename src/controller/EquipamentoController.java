package controller;

import java.io.IOException;
import java.util.List;
import dal.EquipamentoDAO;
import factory.EquipamentoFactory;
import model.Equipamento;
import utils.Log;

public class EquipamentoController {

    public static void criar(String nome, String tipo, int id, int quantidade, float preco) throws IOException {
        List<Equipamento> equipamentos = carregarTodos();
        Equipamento equipamento = EquipamentoFactory.criarEquipamento(nome, tipo, id, quantidade, preco);
        equipamentos.add(equipamento);
        EquipamentoDAO.salvarEquipamentos(equipamentos);
    }

    public static Equipamento buscar(int id) throws IOException {
        List<Equipamento> equipamentos = carregarTodos();
        for (Equipamento e : equipamentos) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new IOException("Equipamento não encontrado ao buscar. ID: " + id);
    }

    public static List<Equipamento> listar() throws IOException {
        return carregarTodos();
    }

    public static void editar(int id, String nome, String tipo, int quantidade, float preco) throws IOException {
        List<Equipamento> equipamentos = carregarTodos();
        boolean encontrado = false;
        for (int i = 0; i < equipamentos.size(); i++) {
            if (equipamentos.get(i).getId() == id) {
                equipamentos.set(i, EquipamentoFactory.criarEquipamento(nome, tipo, id, quantidade, preco));
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            EquipamentoDAO.salvarEquipamentos(equipamentos);
        } else {
            throw new IOException("Equipamento não encontrado para edição. ID: " + id);
        }
    }

    public static void deletar(int id) throws IOException {
        List<Equipamento> equipamentos = carregarTodos();
        boolean removido = equipamentos.removeIf(e -> e.getId() == id);
        if (removido) {
            EquipamentoDAO.salvarEquipamentos(equipamentos);
        } else {
            throw new IOException("Equipamento não encontrado para exclusão. ID: " + id);
        }
    }

    public static int gerarNovoId() throws IOException {
        List<Equipamento> equipamentos = carregarTodos();
        if (equipamentos.isEmpty())
            return 1;
        return equipamentos.stream().mapToInt(Equipamento::getId).max().getAsInt() + 1;
    }

    private static List<Equipamento> carregarTodos() throws IOException {
        try {
            return EquipamentoDAO.carregarEquipamentos();
        } catch (IOException e) {
            Log.setError("Erro ao carregar equipamentos: " + e.getMessage());
            return null;
        }
    }
}