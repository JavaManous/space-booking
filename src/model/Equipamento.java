package model;

import java.io.Serializable;

public class Equipamento implements Serializable {
    private String nome, tipo;
    private int id, quantidade;
    private float preco;

    public Equipamento(String nome, String tipo, int id, int quantidade, float preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.id = id;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Equipamento [" +
                "nome=" + nome +
                ", tipo=" + tipo +
                ", id=" + id +
                ", quantidade=" + quantidade +
                ", preco=" + preco + "]";
    }

    public static Equipamento fromString(String equipamentoStr) {
        equipamentoStr = equipamentoStr.replace("Equipamento [", "").replace("]", "");
        String[] campos = equipamentoStr.split(", ");
        String nome = "", tipo = "";
        int id = 0, quantidade = 0;
        float preco = 0f;

        for (String campo : campos) {
            String[] chaveValor = campo.split("=", 2);
            switch (chaveValor[0]) {
                case "nome":
                    nome = chaveValor[1];
                    break;
                case "tipo":
                    tipo = chaveValor[1];
                    break;
                case "id":
                    id = Integer.parseInt(chaveValor[1]);
                    break;
                case "quantidade":
                    quantidade = Integer.parseInt(chaveValor[1]);
                    break;
                case "preco":
                    preco = Float.parseFloat(chaveValor[1]);
                    break;
            }
        }
        return new Equipamento(nome, tipo, id, quantidade, preco);
    }
}