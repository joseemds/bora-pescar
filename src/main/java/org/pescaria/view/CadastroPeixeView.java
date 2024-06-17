package org.pescaria.view;

import java.util.Scanner;

import org.pescaria.entity.Peixe;
import org.pescaria.service.AuthService;
import org.pescaria.service.PeixeService;
import org.pescaria.exception.DAOException;

public class CadastroPeixeView implements View {
    private PeixeService peixeService = new PeixeService();

    @Override
    public void startView() {
        if (!AuthService.isAdmin()) {
            System.out.println("Apenas administradores podem cadastrar peixes.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Cadastro de Peixe:");
        System.out.print("Especie: ");
        String especie = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Peixe peixe = new Peixe(especie, descricao);
        try {
            peixeService.cadastrarPeixe(peixe);
            System.out.println("Peixe cadastrado com sucesso.");
        } catch (DAOException e) {
            System.err.println("Erro ao cadastrar peixe: " + e.getMessage());
        }
    }

    public void cadastrarPeixe(Peixe peixe) {
        if (!AuthService.isAdmin()) {
            System.out.println("Apenas administradores podem cadastrar peixes.");
            return;
        }

        try {
            peixeService.cadastrarPeixe(peixe);
            System.out.println("Peixe cadastrado com sucesso.");
        } catch (DAOException e) {
            System.err.println("Erro ao cadastrar peixe: " + e.getMessage());
        }
    }
}
