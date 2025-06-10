package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Pessoa {
    private Date dataCadastro;
    private List<Reserva> reservas = new ArrayList<>();

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

    @Override
    public boolean validarDocumento() {
        String cpf = getCpf();

        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        boolean todosDigitosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }

        if (todosDigitosIguais) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9) {
            primeiroDigito = 0;
        }

        if (Character.getNumericValue(cpf.charAt(9))!= primeiroDigito) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) {
            segundoDigito = 0;
        }

        return Character.getNumericValue(cpf.charAt(10)) == segundoDigito;

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
