package org.pescaria.view;

import java.util.Scanner;

import org.pescaria.entity.Pescaria;
import org.pescaria.exception.DAOException;
import org.pescaria.service.PescariaService;

public class PescariaView implements View {
    private PescariaService pescariaService = new PescariaService();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void startView() {
        System.out.println("Pescaria View:");
        // interagir com o usuário
    }

    public void cadastrarPescaria(Pescaria pescaria) {
        try {
            pescariaService.cadastrarPescaria(pescaria);
            System.out.println("Pescaria cadastrada com sucesso.");
        } catch (DAOException e) {
            System.err.println("Erro ao cadastrar pescaria: " + e.getMessage());
        }
    }

    public void listarPescarias() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPescarias'");
    }
}
