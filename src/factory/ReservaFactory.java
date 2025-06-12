package factory;

import model.Reserva;
import model.Cliente;
import model.Sala;
import java.util.Date;

public abstract class ReservaFactory {
    public static Reserva criarReserva(
        int id,
        Cliente cliente,
        Sala sala,
        Date dataInicio,
        Date dataFim,
        float valorTotal,
        float valorHora,
        Reserva.StatusReserva status
    ) {
        return new Reserva(id, cliente, sala, dataInicio, dataFim, valorTotal, valorHora, status);
    }
}