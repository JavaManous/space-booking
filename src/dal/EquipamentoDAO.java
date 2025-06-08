package dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Equipamento;

public abstract class EquipamentoDAO {
    private static final String CAMINHO = "./dados";

    public static void salvarEquipamento(Equipamento equipamento) throws IOException, ClassNotFoundException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        List<Equipamento> equipamentos = listarEquipamentos();
        equipamentos.add(equipamento);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/equipamentos.ser"))) {
            oos.writeObject(equipamentos);
        }
    }

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

    public static Equipamento buscarEquipamento(int id) throws IOException, ClassNotFoundException {
        List<Equipamento> equipamentos = listarEquipamentos();
        for (Equipamento eq : equipamentos) {
            if (eq.getId() == id) {
                return eq;
            }
        }
        return null;
    }

    public static void atualizarEquipamento(Equipamento equipamentoAtualizado) throws IOException, ClassNotFoundException {
        List<Equipamento> equipamentos = listarEquipamentos();
        for (int i = 0; i < equipamentos.size(); i++) {
            if (equipamentos.get(i).getId() == equipamentoAtualizado.getId()) {
                equipamentos.set(i, equipamentoAtualizado);
                break;
            }
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/equipamentos.ser"))) {
            oos.writeObject(equipamentos);
        }
    }

    public static void removerEquipamento(int id) throws IOException, ClassNotFoundException {
        List<Equipamento> equipamentos = listarEquipamentos();
        equipamentos.removeIf(eq -> eq.getId() == id);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/equipamentos.ser"))) {
            oos.writeObject(equipamentos);
        }
    }
}