package model;

import java.util.Date;

public class Reserva {
    private int id;
    private Cliente cliente;
    private Sala sala;
    private Date dataInicio, dataFim;
    private float valorTotal, valorHora;
    private StatusReserva status;

    public enum StatusReserva {
        PENDENTE, CONFIRMADA, CANCELADA, CONCLUIDA
    }

    public Reserva(int id, Cliente cliente, Sala sala, Date dataInicio, Date dataFim, float valorTotal, float valorHora,
            StatusReserva status) {
        this.id = id;
        this.cliente = cliente;
        this.sala = sala;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
        this.valorHora = valorHora;
        this.status = status;
    }

    public float calcularValorAluguel(int horas) { 
    return valorHora * horas;
    }

    public float calcularValorAluguel(int horas, float desconto) {
    float valorTotal = valorHora * horas;
    return valorTotal - (valorTotal * desconto / 100);
    }

    public int calcularDuracao() {
        if (dataInicio == null || dataFim == null) {
            return 0;
        }

        long diferencaMillis = dataFim.getTime() - dataInicio.getTime();
        return (int) (diferencaMillis / (1000 * 60 * 60));
    }

    public float calcularValorTotal() {
        if (sala == null) {
            return 0;
        }

        int duracao = calcularDuracao();
        return calcularValorAluguel(duracao);
    }

    public boolean confirmarReserva() {
        if (status == StatusReserva.PENDENTE) {
            status = StatusReserva.CONFIRMADA;
            return true;
        }
        return false;
    }

    public boolean cancelarReserva() {
        if (status == StatusReserva.PENDENTE || status == StatusReserva.CONFIRMADA) {
            status = StatusReserva.CANCELADA;
            return true;
        }
        return false;
    }

    public boolean finalizarReserva() {
        if (status == StatusReserva.CONFIRMADA) {
            status = StatusReserva.CONCLUIDA;
            return true;
        }
        return false;
    }

    public boolean isAtiva() {
        return status == StatusReserva.CONFIRMADA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float valorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", cliente=" + cliente + ", sala=" + sala + ", dataInicio=" + dataInicio
                + ", dataFim=" + dataFim + ", valorTotal=" + valorTotal + ", status=" + status + "]";
    }
}
