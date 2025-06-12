package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Pessoa implements Serializable {
    private Date dataCadastro;
    private List<Reserva> reservas = new ArrayList<>();

    public Cliente(String nome, String email, String endereco, String cpf, String telefone) {
        super(0, nome, email, endereco, cpf, telefone); // ID genérico temporário
        this.dataCadastro = new Date(); // Cria com a data atual
    }

    public Cliente(int id, String nome, String email, String endereco, String cpf, String telefone, Date dataCadastro) {
        super(id, nome, email, endereco, cpf, telefone);
        this.dataCadastro = dataCadastro;
    }

    public int calcularFrequencia() {
        return reservas.size();
    }

    public boolean verificarReservasAtivas() {
        for (Reserva reserva : reservas) {
            if (reserva.isAtiva()) {
                return true;
            }
        }
        return false;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "Cliente [dataCadastro=" + dataCadastro + ", id=" + getId() + ", nome=" + getNome()
                + ", email=" + getEmail() + ", endereco=" + getEndereco() + ", cpf=" + getCpf()
                + ", telefone=" + getTelefone() + "]";
    }

    public Cliente fromString(String clienteStr) {
        throw new UnsupportedOperationException("Método não implementado");
    }
}
