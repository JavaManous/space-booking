package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dal.SalaDAO;
import factory.SalaFactory;
import model.Sala;
import utils.Log;
import model.Equipamento;

public abstract class SalaController {

    public static void criar(int numeroSala, int capacidade, List<Integer> idsEquipamentos)
            throws Exception, IOException {
        for (Sala sala : SalaDAO.carregar()) {
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
                Equipamento eq = EquipamentoController.buscar(id);
                if (eq != null) {
                    equipamentos.add(eq);
                }
            }
        }
        Sala novaSala = SalaFactory.criarSala(numeroSala, capacidade, equipamentos);

        try {
            SalaDAO.carregar().add(novaSala);
            SalaDAO.salvar(SalaDAO.carregar());
        } catch (Exception erro) {
            Log.setError("Erro ao salvar sala: " + erro.getMessage());
            throw new IOException("Ocorreu um erro ao salvar a sala");
        }
    }

    public static Sala buscarPorNumeroSala(int numeroSala) throws Exception {
        for (Sala sala : SalaDAO.carregar()) {
            if (sala.getNumeroSala() == numeroSala) {
                return sala;
            }
        }
        Log.setError("Não foi encontrado sala com o numero " + numeroSala);
        throw new Exception("Não foi encontrado sala com o numero " + numeroSala);
    }

    public static List<Sala> listar() throws ClassNotFoundException, IOException {

        List<Sala> salasSalvas = SalaDAO.carregar();

        if (salasSalvas.isEmpty() || salasSalvas == null) {
            return null;
        }

        return salasSalvas;
    }

    public static void editar(int numeroSala, int novoNumeroSala, int capacidade, List<Integer> idsEquipamentos)
            throws Exception {
        Sala salaAMudar = buscarPorNumeroSala(numeroSala);

        List<Equipamento> equipamentos = new ArrayList<>();
        if (idsEquipamentos.isEmpty()) {
            equipamentos = null;
        } else {

            for (int id : idsEquipamentos) {
                Equipamento eq = EquipamentoController.buscar(id);
                if (eq != null) {
                    equipamentos.add(eq);
                }
            }
        }
        salaAMudar.setNumeroSala(novoNumeroSala);
        salaAMudar.setCapacidadePessoas(capacidade);
        salaAMudar.setEquipamentos(equipamentos);

        SalaDAO.carregar().removeIf(sala -> sala.getNumeroSala() == numeroSala);

        for (Sala sala : SalaDAO.carregar()) {

            if (sala.getNumeroSala() == numeroSala) {

                Log.setError("Sala com número " + salaAMudar.getNumeroSala() + " já existe");
                throw new Exception("Sala com número " + salaAMudar.getNumeroSala() + " já existe");
            }
        }

        try {
            SalaDAO.carregar().add(salaAMudar);
            SalaDAO.salvar(SalaDAO.carregar());
        } catch (Exception erro) {
            Log.setError("Erro ao editar sala: " + erro.getMessage());
            throw new IOException("Ocorreu um erro ao editar a sala");
        }
    }

    public static void deletar(int numeroSala) throws Exception {
        if (buscarPorNumeroSala(numeroSala) == null) {
            throw new Exception("Sala " + numeroSala + " não existe para ser deletada");
        }

        try {
            SalaDAO.carregar().removeIf(sala -> sala.getNumeroSala() == numeroSala);
            SalaDAO.salvar(SalaDAO.carregar());

        } catch (Exception erro) {
            Log.setError("Erro ao deletar sala: " + erro.getMessage());
            throw new IOException("Ocorreu um erro ao deletar a sala");
        }
    }

}