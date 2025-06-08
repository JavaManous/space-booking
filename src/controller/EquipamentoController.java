package controller;

import dal.EquipamentoDAO;
import java.util.List;
import java.util.Scanner;

import factory.EquipamentoFactory;
import model.Controller;
import model.Equipamento;

public class EquipamentoController implements Controller {

    private Scanner input;

    public void setInput(Scanner input) {
        this.input = input;
    }

    public Equipamento criarEquipamento() {
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

        return EquipamentoFactory.criarEquipamento(nome, tipo, id, quantidade, preco);
    }

    @Override
    public void criar() throws Exception {
        Equipamento equipamento = criarEquipamento();
        EquipamentoDAO.salvarEquipamento(equipamento);
        System.out.println("Equipamento criado!");
    }

    @Override
    public void listar() throws Exception {
        List<Equipamento> equipamentos = EquipamentoDAO.listarEquipamentos();
        if (equipamentos.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado");
            return;
        }
        for (Equipamento equipamento : equipamentos) {
            System.out.println(equipamento);
        }
    }

    @Override
    public void buscar() throws Exception {
        System.out.println("Digite o ID do equipamento que deseja buscar:");
        int id = input.nextInt();
        input.nextLine();

        Equipamento equipamento = EquipamentoDAO.buscarEquipamento(id);
        if (equipamento != null) {
            System.out.println("Equipamento encontrado:");
            System.out.println(equipamento);
        } else {
            System.out.println("Equipamento não encontrado.");
        }
    }

    @Override
    public void editar() throws Exception {
        System.out.println("Digite o ID do equipamento que deseja editar: ");
        int id = input.nextInt();
        input.nextLine();

        Equipamento equipamento = EquipamentoDAO.buscarEquipamento(id);
        if (equipamento == null) {
            System.out.println("Equipamento não encontrado.");
            return;
        }

        System.out.println("Novo nome: ");
        equipamento.setNome(input.nextLine());
        System.out.println("Novo tipo: ");
        equipamento.setTipo(input.nextLine());
        System.out.println("Nova quantidade: ");
        equipamento.setQuantidade(input.nextInt());
        System.out.println("Novo preço: ");
        equipamento.setPreco(input.nextFloat());
        input.nextLine();

        EquipamentoDAO.atualizarEquipamento(equipamento);
        System.out.println("Equipamento atualizado com sucesso!");
    }

    @Override
    public void deletar() throws Exception {
        System.out.println("Digite o ID do equipamento que deseja deletar: ");
        int id = input.nextInt();
        input.nextLine();

        Equipamento equipamento = EquipamentoDAO.buscarEquipamento(id);
        if (equipamento == null) {
            System.out.println("Equipamento não encontrado.");
            return;
        }

        EquipamentoDAO.removerEquipamento(id);
        System.out.println("Equipamento removido com sucesso!");
    }
}