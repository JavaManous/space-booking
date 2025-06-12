package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dal.EquipamentoDAO;
import dal.SalaDAO;
import factory.SalaFactory;
import model.Sala;
import utils.Log;
import model.Equipamento;

public class SalaController {
    private List<Sala> salasSalvas;

    public SalaController() throws IOException, ClassNotFoundException {
        this.salasSalvas = SalaDAO.carregar();
    }

    public void criar(int numeroSala, int capacidade, List<Integer> idsEquipamentos) throws Exception, IOException {
        for (Sala sala : this.salasSalvas) {
            if (sala.getNumeroSala() == numeroSala) {
                Log.setError("Sala com número " + sala.getNumeroSala() + " já existe");
                throw new Exception("Sala com número " + sala.getNumeroSala() + " já existe");
            }
        }

        List<Equipamento> equipamentos = new ArrayList<>();
        if (idsEquipamentos.isEmpty()) {
            equipamentos = null;
        } else {

            for (int id : idsEquipamentos) {
                Equipamento eq = EquipamentoDAO.buscarPorId(id);
                if (eq != null) {
                    equipamentos.add(eq);
                }
            }
        }
        Sala novaSala = SalaFactory.criarSala(numeroSala, capacidade, equipamentos);

        try {

            this.salasSalvas.add(novaSala);
            SalaDAO.salvar(this.salasSalvas);
        } catch (Exception erro) {
            Log.setError("Erro ao salvar sala: " + erro.getMessage());
            throw new IOException("Ocorreu um erro ao salvar a sala");
        }
    }

    public Sala buscarPorNumeroSala(int numeroSala) throws Exception {

        for (Sala sala : this.salasSalvas) {
            if (sala.getNumeroSala() == numeroSala) {
                return sala;
            }
        }

        Log.setError("Não foi encontrado sala com o numero " + numeroSala);
        throw new Exception("Não foi encontrado sala com o numero " + numeroSala);
    }

    public List<Sala> listar() {

        if (this.salasSalvas.isEmpty() || this.salasSalvas == null) {
            return null;
        }

        return salasSalvas;
    }

    public void editar(int numeroSala, int novoNumeroSala, int capacidade, List<Integer> idsEquipamentos)
            throws Exception {
        Sala salaAMudar = buscarPorNumeroSala(numeroSala);

        List<Equipamento> equipamentos = new ArrayList<>();
        if (idsEquipamentos.isEmpty()) {
            equipamentos = null;
        } else {

            for (int id : idsEquipamentos) {
                Equipamento eq = EquipamentoDAO.buscarPorId(id);
                if (eq != null) {
                    equipamentos.add(eq);
                }
            }
        }
        salaAMudar.setNumeroSala(novoNumeroSala);
        salaAMudar.setCapacidadePessoas(capacidade);
        salaAMudar.setEquipamentos(equipamentos);

        this.salasSalvas.removeIf(sala -> sala.getNumeroSala() == numeroSala);

        for (Sala sala : this.salasSalvas) {

            if (sala.getNumeroSala() == numeroSala) {

                Log.setError("Sala com número " + salaAMudar.getNumeroSala() + " já existe");
                throw new Exception("Sala com número " + salaAMudar.getNumeroSala() + " já existe");
            }
        }

        try {
            this.salasSalvas.add(salaAMudar);
            SalaDAO.salvar(this.salasSalvas);
        } catch (Exception erro) {
            Log.setError("Erro ao editar sala: " + erro.getMessage());
            throw new IOException("Ocorreu um erro ao editar a sala");
        }
    }

    public void deletar(int numeroSala) throws Exception {
        if (buscarPorNumeroSala(numeroSala) == null) {
            throw new Exception("Sala " + numeroSala + " não existe para ser deletada");
        }

        try {
            this.salasSalvas.removeIf(sala -> sala.getNumeroSala() == numeroSala);
            SalaDAO.salvar(this.salasSalvas);

        } catch (Exception erro) {
            Log.setError("Erro ao deletar sala: " + erro.getMessage());
            throw new IOException("Ocorreu um erro ao deletar a sala");
        }
    }

}