package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sala implements Serializable {
    private int numeroSala;
    private int capacidadePessoas;
    private List<Equipamento> equipamentos = new ArrayList<>();

    public Sala(int numeroSala, int capacidadePessoas, List<Equipamento> equipamentos) {
        this.numeroSala = numeroSala;
        this.capacidadePessoas = capacidadePessoas;
        this.equipamentos = equipamentos;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCapacidadePessoas() {
        return capacidadePessoas;
    }

    public void setCapacidadePessoas(int capacidadePessoas) {
        this.capacidadePessoas = capacidadePessoas;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public String toString() {
        return "Sala [" +
                "numeroSala='" + this.numeroSala + '\'' +
                ", capacidadePessoas=" + this.capacidadePessoas +
                ", equipamentos=" + this.equipamentos.toString() +
                ']';
    }

    public static Sala fromString(String salaStr) {
        // Exemplo de string:
        // Sala [numeroSala='1', capacidadePessoas=10, equipamentos=[Equipamento [...],
        // Equipamento [...]]]
        salaStr = salaStr.replace("Sala [", "").replace("]", "");
        String[] partes = salaStr.split(", equipamentos=", 2);

        // Extrai numeroSala e capacidadePessoas
        String[] campos = partes[0].split(", ");
        int numeroSala = Integer.parseInt(campos[0].split("=")[1].replace("'", ""));
        int capacidadePessoas = Integer.parseInt(campos[1].split("=")[1]);

        // Extrai equipamentos
        List<Equipamento> equipamentos = new ArrayList<>();
        if (partes.length > 1) {
            String equipamentosStr = partes[1];
            // Remove colchetes externos
            if (equipamentosStr.startsWith("["))
                equipamentosStr = equipamentosStr.substring(1);
            if (equipamentosStr.endsWith("]"))
                equipamentosStr = equipamentosStr.substring(0, equipamentosStr.length() - 1);

            // Divide cada equipamento (cuidado com vírgulas internas)
            String[] equipamentosArr = equipamentosStr.split("(?<=\\]), ");
            for (String eqStr : equipamentosArr) {
                eqStr = eqStr.trim();
                if (!eqStr.isEmpty()) {
                    equipamentos.add(Equipamento.fromString(eqStr));
                }
            }
        }

        return new Sala(numeroSala, capacidadePessoas, equipamentos);
    }

}
