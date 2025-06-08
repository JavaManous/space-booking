package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dal.SalaDAO;
import factory.SalaFactory;
import model.Controller;
import model.Sala;
import model.Equipamento;

public class SalaController implements Controller {

    private Scanner input;

    public void setInput(Scanner input) {
        this.input = input;
    }

    public void criar() throws Exception {
        System.out.println("Digite o número da sala:");
        int numeroSala = input.nextInt();

        System.out.println("Digite a capacidade de pessoas da sala:");
        int capacidade = input.nextInt();

        System.out.println("Quantos equipamentos deseja adicionar?");
        int quantidadeEquipamento = input.nextInt();
        input.nextLine();

        List<Equipamento> equipamentos = new ArrayList<>();
        EquipamentoController equipamentoController = new EquipamentoController();
        equipamentoController.setInput(this.input);

        for (int i = 0; i < quantidadeEquipamento; i++) {
            Equipamento equipamento = equipamentoController.criarEquipamento();
            equipamentos.add(equipamento);
        }

        Sala novaSala = SalaFactory.criarSala(numeroSala, capacidade, equipamentos);
        SalaDAO.salvarSala(novaSala);
    }

    public void buscar() throws Exception {

        System.out.println("Digite o número da sala que deseja buscar:");
        int numeroSala = input.nextInt();

        Sala sala = SalaDAO.buscarSala(numeroSala);
        if (sala != null) {
            System.out.println(("Detalhes da Sala:"));
            System.err.println(sala);
        } else {
            System.out.println("Sala não encontrada: " + numeroSala);
        }
    }

    public void listar() throws Exception {
        // Implementação do método listar
        try {
            List<Sala> salas = SalaDAO.listarSalas();
            if (salas.isEmpty()) {
                System.out.println("Nenhuma sala cadastrada.");
                return;
            }

            for (Sala sala : salas) {
                System.out.println("Imprimindo salas..");
                System.out.println(sala.toString());
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar salas: \n" + e.getMessage() + "\n");
        }

    }

    public void editar() throws Exception {
        // Implementação do método editar
    }

    public void deletar() throws Exception {
        // Implementação do método deletar
    }

}
