package org.pescaria.view;

import java.util.Scanner;

import org.pescaria.entity.Peixe;
import org.pescaria.service.AuthService;
import org.pescaria.service.PeixeService;
import org.pescaria.exception.DAOException;

public class CadastroPeixeView implements View {
    private PeixeService peixeService = new PeixeService();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    @Override
    public void startView() {
        if (!AuthService.isAdmin()) {
            System.out.println(ANSI_YELLOW + "Apenas administradores podem cadastrar peixes." + ANSI_RESET);

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
            System.out.println(ANSI_GREEN + "Peixe cadastrado com sucesso." + ANSI_RESET);

        } catch (DAOException e) {
            System.err.println(ANSI_RED + "Erro ao cadastrar peixe: " + e.getMessage() + ANSI_RESET);
        }
    }

}
