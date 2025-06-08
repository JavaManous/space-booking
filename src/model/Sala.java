package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sala implements Serializable{
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
        return "Sala{" +
                "numeroSala='" + numeroSala + '\'' +
                ", capacidadePessoas=" + capacidadePessoas +
                ", equipamentos=" + equipamentos +
                '}';
    }
}
