package controller;

import java.util.List;
import java.util.Scanner;

import dal.SalaDAO;
import factory.SalaFactory;
import model.Controller;
import model.Sala;

public class SalaController implements Controller {

    public void criar() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o número da sala:");
        int numeroSala = input.nextInt();

        System.out.println("Digite a capacidade de pessoas da sala:");
        int capacidade = input.nextInt();

        System.out.println("Selecione os equipamentos da sala (separados por vírgula):");
        String[] equipamentosStr = input.next().split(",");
        Sala novaSala = SalaFactory.criarSala(numeroSala, capacidade, equipamentosStr);

        SalaDAO.salvarSala(novaSala);
        // input.close();
    }

    public void buscar() {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o número da sala que deseja buscar:");
        int numeroSala = input.nextInt();
        input.close();

        Sala sala = SalaDAO.buscarSala(numeroSala);
        if (sala != null) {
            System.out.println(("Detalhes da Sala:"));
            System.err.println(sala);
        } else {
            System.out.println("Sala não encontrada: " + numeroSala);
        }
    }

    public void listar() {
        // Implementação do método listar
        System.out.println("Listando todas as salas:");

        List<Sala> salas = SalaDAO.listarSalas();
        for (Sala sala : salas) {
            System.out.println(sala.toString());
        }

    }

    public void editar() {
        // Implementação do método editar
    }

    public void deletar() {
        // Implementação do método deletar
    }

}
