package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public abstract class Log {
    public static void setError(String mensagem) {
        try {
            File logDir = new File("logs");
            logDir.mkdir();

            String dataAtual = java.time.LocalDate.now().toString();
            mensagem = dataAtual + " | " + mensagem;

            try (PrintStream logErro = new PrintStream(new FileOutputStream("logs/erro.txt", true))) {
                logErro.println(mensagem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
