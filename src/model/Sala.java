package model;

public class Sala {
    private int numeroSala;
    private int capacidadePessoas;
    private String[] equipamentos; // deve ser um array de equipamentos: Equipamento
    
    public Sala(int numeroSala, int capacidadePessoas, String[] equipamentos) {
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

    public String[] getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(String[] equipamentos) {
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
