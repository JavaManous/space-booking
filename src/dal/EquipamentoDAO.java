package dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import model.Equipamento;
import utils.Log;

public abstract class EquipamentoDAO {

    private static final String CAMINHO = "./dados";

    public static void salvarEquipamentos(List<Equipamento> equipamentos) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/equipamentos.ser"))) {
            oos.writeObject(equipamentos);
        } catch (IOException e) {
            Log.setError("Erro ao salvar equipamentos: " + e.getMessage());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Equipamento> carregarEquipamentos() throws IOException {
        File arquivo = new File(CAMINHO + "/equipamentos.ser");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(arquivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (List<Equipamento>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Log.setError("Erro ao carregar equipamentos: " + e.getMessage());
            throw new IOException("Erro ao carregar equipamentos.", e);
        }
    }
}