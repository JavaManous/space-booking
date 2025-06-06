package model;

public class Equipamento {
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
        return "Equipamento{" + 
                "nome=" + nome + 
                ", tipo=" + tipo + 
                ", id=" + id + 
                ", quantidade=" + quantidade + 
                ", preco=" + preco + "}";
    }
    
}