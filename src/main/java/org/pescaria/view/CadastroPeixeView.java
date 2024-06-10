package org.pescaria.view;

import org.pescaria.entity.Peixe;
import org.pescaria.service.PeixeService;
import org.pescaria.exception.DAOException;

import java.util.Scanner;

public class CadastroPeixeView implements View {
    private PeixeService peixeService = new PeixeService();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void startView() {
        System.out.println("Cadastro de Peixe:");
        // interagindo com o usuário
    }

    public void cadastrarPeixe(Peixe peixe) {
        try {
            peixeService.cadastrarPeixe(peixe);
            System.out.println("Peixe cadastrado com sucesso.");
        } catch (DAOException e) {
            System.err.println("Erro ao cadastrar peixe: " + e.getMessage());
        }
    }
}
