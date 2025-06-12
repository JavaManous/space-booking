package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dal.EquipamentoDAO;
import factory.EquipamentoFactory;
import model.Controller;
import model.Equipamento;
import utils.Log;

public class EquipamentoController {
    private Scanner input;

    public void setInput(Scanner input) {
        this.input = input;
    }

    public void criar() {
        try {
            System.out.println("Digite o nome do equipamento: ");
            String nome = input.nextLine();

            System.out.println("Digite o tipo do equipamento: ");
            String tipo = input.nextLine();

            System.out.println("Digite o id do equipamento: ");
            int id = input.nextInt();

            System.out.println("Digite a quantidade: ");
            int quantidade = input.nextInt();

            System.out.println("Digite o preço: ");
            float preco = input.nextFloat();
            input.nextLine();

            Equipamento equipamento = EquipamentoFactory.criarEquipamento(nome, tipo, id, quantidade, preco);
            EquipamentoDAO.salvarEquipamento(equipamento);
            System.out.println("Equipamento cadastrado com sucesso!");
            Log.setError("Equipamento cadastrado: " + equipamento.getId() + " - " + equipamento.getNome());
        } catch (Exception e) {
            Log.setError("Erro ao criar equipamento: " + e.getMessage());
            System.out.println("Erro ao cadastrar equipamento.");
        }
    }

    public void buscar() {
        try {
            System.out.println("Digite o ID do equipamento que deseja buscar:");
            int id = input.nextInt();
            input.nextLine();

            Equipamento equipamento = EquipamentoDAO.buscarEquipamento(id);
            if (equipamento != null) {
                System.out.println("Detalhes do Equipamento:");
                System.out.println(equipamento);
            } else {
                System.out.println("Equipamento não encontrado: " + id);
                Log.setError("Equipamento não encontrado ao buscar. ID: " + id);
            }
        } catch (Exception e) {
            Log.setError("Erro ao buscar equipamento: " + e.getMessage());
            System.out.println("Erro ao buscar equipamento.");
        }
    }

    public static List<Equipamento> listar() {
        try {
            List<Equipamento> equipamentos = EquipamentoDAO.listarEquipamentos();
            if (equipamentos.isEmpty()) {
                return new ArrayList<>();
            }

            return equipamentos;
        } catch (Exception e) {
            Log.setError("Erro ao listar equipamentos: " + e.getMessage());
            System.out.println("Erro ao listar equipamentos.");
        }
        return new ArrayList<>();
    }

    public void editar() {
        try {
            System.out.println("Digite o ID do equipamento que deseja editar: ");
            int id = input.nextInt();
            input.nextLine();

            Equipamento equipamentoExistente = EquipamentoDAO.buscarEquipamento(id);
            if (equipamentoExistente == null) {
                System.out.println("Equipamento não encontrado: " + id);
                Log.setError("Equipamento não encontrado para edição. ID: " + id);
                return;
            }

            System.out.println("Digite o novo nome do equipamento: ");
            String nome = input.nextLine();

            System.out.println("Digite o novo tipo do equipamento: ");
            String tipo = input.nextLine();

            System.out.println("Digite a nova quantidade: ");
            int quantidade = input.nextInt();

            System.out.println("Digite o novo preço: ");
            float preco = input.nextFloat();
            input.nextLine();

            Equipamento equipamentoAtualizado = EquipamentoFactory.criarEquipamento(nome, tipo, id, quantidade, preco);

            EquipamentoDAO.atualizarEquipamento(id, equipamentoAtualizado);
            System.out.println("Equipamento editado com sucesso!");
            Log.setError("Equipamento editado: " + id);
        } catch (Exception e) {
            Log.setError("Erro ao editar equipamento: " + e.getMessage());
            System.out.println("Erro ao editar equipamento.");
        }
    }

    public void deletar() {
        try {
            System.out.println("Digite o ID do equipamento que deseja deletar: ");
            int id = input.nextInt();
            input.nextLine();

            Equipamento equipamento = EquipamentoDAO.buscarEquipamento(id);
            if (equipamento == null) {
                System.out.println("Equipamento não encontrado: " + id);
                Log.setError("Equipamento não encontrado para exclusão. ID: " + id);
                return;
            }

            EquipamentoDAO.removerEquipamento(id);
            System.out.println("Equipamento deletado com sucesso!");
            Log.setError("Equipamento deletado: " + id);
        } catch (Exception e) {
            Log.setError("Erro ao deletar equipamento: " + e.getMessage());
            System.out.println("Erro ao deletar equipamento.");
        }
    }
}