package controller;

import java.util.List;
import java.util.ArrayList;
import dal.EquipamentoDAO;
import factory.EquipamentoFactory;
import model.Equipamento;
import utils.Log;

public class EquipamentoController {

    public void criar(String nome, String tipo, int id, int quantidade, float preco) throws Exception {
        List<Equipamento> equipamentos = carregarTodos();
        Equipamento equipamento = EquipamentoFactory.criarEquipamento(nome, tipo, id, quantidade, preco);
        equipamentos.add(equipamento);
        EquipamentoDAO.salvarEquipamentos(equipamentos);
        Log.setError("Equipamento cadastrado: " + equipamento.getId() + " - " + equipamento.getNome());
    }

    public Equipamento buscar(int id) throws Exception {
        List<Equipamento> equipamentos = carregarTodos();
        for (Equipamento e : equipamentos) {
            if (e.getId() == id) {
                return e;
            }
        }
        Log.setError("Equipamento não encontrado ao buscar. ID: " + id);
        return null;
    }

    public List<Equipamento> listar() throws Exception {
        return carregarTodos();
    }

    public void editar(int id, String nome, String tipo, int quantidade, float preco) throws Exception {
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
            Log.setError("Equipamento editado: " + id);
        } else {
            Log.setError("Equipamento não encontrado para edição. ID: " + id);
            throw new Exception("Equipamento não encontrado.");
        }
    }

    public void deletar(int id) throws Exception {
        List<Equipamento> equipamentos = carregarTodos();
        boolean removido = equipamentos.removeIf(e -> e.getId() == id);
        if (removido) {
            EquipamentoDAO.salvarEquipamentos(equipamentos);
            Log.setError("Equipamento deletado: " + id);
        } else {
            Log.setError("Equipamento não encontrado para exclusão. ID: " + id);
            throw new Exception("Equipamento não encontrado.");
        }
    }

    public int gerarNovoId() throws Exception {
        List<Equipamento> equipamentos = carregarTodos();
        if (equipamentos.isEmpty()) return 1;
        return equipamentos.stream().mapToInt(Equipamento::getId).max().getAsInt() + 1;
    }

    private List<Equipamento> carregarTodos() throws Exception {
        try {
            return EquipamentoDAO.carregarEquipamentos();
        } catch (Exception e) {
            Log.setError("Erro ao carregar equipamentos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}