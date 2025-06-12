package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dal.ReservaDAO;
import factory.ReservaFactory;
import model.Reserva;
import model.Cliente;
import model.Sala;
import utils.Log;

public class ReservaController {

    private List<Reserva> reservas;

    public ReservaController() {
        try {
            this.reservas = ReservaDAO.listarReservas();
        } catch (Exception e) {
            this.reservas = new ArrayList<>();
        }
    }

    public Reserva criar(int idCliente, Cliente cliente, Sala sala, Date dataInicio, Date dataFim, float valorHora, Reserva.StatusReserva status) throws Exception {
        int novoId = gerarNovoId();
        float valorTotal = calcularValorTotal(dataInicio, dataFim, valorHora);
        Reserva reserva = ReservaFactory.criarReserva(novoId, cliente, sala, dataInicio, dataFim, valorTotal, valorHora, status);
        reservas.add(reserva);
        ReservaDAO.salvarReservas(reservas);
        return reserva;
    }

    public Reserva buscar(int id) throws Exception {
        for (Reserva r : reservas) {
            if (r.getId() == id) return r;
        }
        Log.setError("Reserva não encontrada: " + id);
        throw new Exception("Reserva não encontrada: " + id);
    }

    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }

    public Reserva editar(int id, Cliente cliente, Sala sala, Date dataInicio, Date dataFim, float valorHora, Reserva.StatusReserva status) throws Exception {
        float valorTotal = calcularValorTotal(dataInicio, dataFim, valorHora);
        Reserva reservaAtualizada = ReservaFactory.criarReserva(id, cliente, sala, dataInicio, dataFim, valorTotal, valorHora, status);
        boolean encontrado = false;
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getId() == id) {
                reservas.set(i, reservaAtualizada);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            ReservaDAO.salvarReservas(reservas);
            return reservaAtualizada;
        } else {
            Log.setError("Reserva não encontrada para edição. ID: " + id);
            throw new Exception("Reserva não encontrada para edição. ID: " + id);
        }
    }

    public void deletar(int id) throws Exception {
        boolean removido = reservas.removeIf(r -> r.getId() == id);
        if (removido) {
            ReservaDAO.salvarReservas(reservas);
        } else {
            Log.setError("Reserva não encontrada para exclusão. ID: " + id);
            throw new Exception("Reserva não encontrada: " + id);
        }
    }

    public int gerarNovoId() {
        if (reservas.isEmpty()) return 1;
        return reservas.stream().mapToInt(Reserva::getId).max().getAsInt() + 1;
    }

    private float calcularValorTotal(Date dataInicio, Date dataFim, float valorHora) {
        if (dataInicio == null || dataFim == null) return 0;
        long diferencaMillis = dataFim.getTime() - dataInicio.getTime();
        int horas = (int) (diferencaMillis / (1000 * 60 * 60));
        return valorHora * horas;
    }
}