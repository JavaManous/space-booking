package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dal.EquipamentoDAO;
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

        try {
            List<Equipamento> equipamentosDisponiveis = EquipamentoDAO.listarEquipamentos();
            if (equipamentosDisponiveis.isEmpty()) {
                System.out.println("Nenhum equipamento cadastrado no sistema.");
            } else {
                System.out.println("Equipamentos disponíveis:");
                for (Equipamento eq : equipamentosDisponiveis) {
                    System.out.println("ID: " + eq.getId() + " | Nome: " + eq.getNome() + " | Tipo: " + eq.getTipo());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar equipamentos: " + e.getMessage());
        }

        System.out.println("Quantos equipamentos deseja adicionar?");
        int quantidadeEquipamento = input.nextInt();
        input.nextLine();

        List<Equipamento> equipamentos = new ArrayList<>();
        for (int i = 0; i < quantidadeEquipamento; i++) {
            System.out.println("Digite o ID do equipamento que deseja adicionar à sala:");
            int idEquipamento = input.nextInt();
            input.nextLine();
            Equipamento equipamento = EquipamentoDAO.buscarEquipamento(idEquipamento);
            if (equipamento != null) {
                equipamentos.add(equipamento);
                System.out.println("Equipamento adicionado.");
            } else {
                System.out.println("Equipamento não encontrado. Tente novamente.");
                i--; 
            }
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