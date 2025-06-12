package dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Equipamento;
import utils.Log;

public abstract class EquipamentoDAO {

    private static final String CAMINHO = "./dados";

    public static void salvarEquipamento(Equipamento equipamento) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        List<Equipamento> equipamentos = new ArrayList<>();
        try {
            equipamentos = listarEquipamentos();
            equipamentos.add(equipamento);
        } catch (Exception e) {
            Log.setError("Erro ao listar equipamentos: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/equipamento.ser"))) {
            oos.writeObject(equipamentos);
        }

        Log.setError("Salvando equipamento: " + equipamento.toString());
    }

    public static Equipamento buscarEquipamento(int id) {
        try {
            List<Equipamento> equipamentos = listarEquipamentos();
            for (Equipamento e : equipamentos) {
                if (e.getId() == id) {
                    return e;
                }
            }
        } catch (Exception e) {
            Log.setError("Erro ao buscar equipamento: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<Equipamento> listarEquipamentos() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/equipamentos.ser");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(arquivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (List<Equipamento>) in.readObject();
        }
    }

    public static int gerarNovoId() throws IOException, ClassNotFoundException {
        List<Equipamento> equipamentos = listarEquipamentos();
        if (equipamentos.isEmpty()) return 1;
        return equipamentos.stream().mapToInt(Equipamento::getId).max().getAsInt() + 1;
    }

    public static void atualizarEquipamento(int id, Equipamento equipamento) throws IOException, ClassNotFoundException {
        List<Equipamento> equipamentos = listarEquipamentos();
        boolean encontrado = false;
        for (int i = 0; i < equipamentos.size(); i++) {
            if (equipamentos.get(i).getId() == id) {
                equipamentos.set(i, equipamento);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/equipamentos.ser"))) {
                oos.writeObject(equipamentos);
            }
            Log.setError("Equipamento atualizado: " + id);
        } else {
            Log.setError("Equipamento não encontrado para edição. ID: " + id);
        }
    }

    public static void removerEquipamento(int id) throws IOException, ClassNotFoundException {
        List<Equipamento> equipamentos = listarEquipamentos();
        boolean removido = equipamentos.removeIf(e -> e.getId() == id);
        if (removido) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/equipamentos.ser"))) {
                oos.writeObject(equipamentos);
            }
            Log.setError("Equipamento removido: " + id);
        } else {
            Log.setError("Equipamento não encontrado para exclusão. ID: " + id);
        }
    }
}