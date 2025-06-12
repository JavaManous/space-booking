package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Sala;
import utils.Log;

public abstract class SalaDAO {
    private static final String CAMINHO = "./dados";
    private static final String ARQUIVO = "/sala.ser";

    public static void salvar(List<Sala> salas) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + ARQUIVO))) {
            oos.writeObject(salas);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Sala> carregar() throws IOException, ClassNotFoundException {

        File arquivo = new File(CAMINHO + ARQUIVO);
        if (!arquivo.exists())
            return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Sala>) ois.readObject();
        }
    }
}
