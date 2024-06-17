package org.pescaria.view;

import java.util.List;

import org.pescaria.entity.Peixe;
import org.pescaria.exception.DAOException;
import org.pescaria.service.PeixeService;

public class PeixeView implements View {
    private PeixeService peixeService = new PeixeService();

    public void listarPeixes() {
        try {
            List<Peixe> peixes = peixeService.listarPeixes();
            System.out.println("Lista de Peixes:");
            for (Peixe peixe : peixes) {
                System.out.println("ID: " + peixe.getId() + ", Especie: " + peixe.getEspecie() + ", Descrição: "
                        + peixe.getDescricao());
            }
        } catch (DAOException e) {
            System.err.println("Erro ao listar peixes: " + e.getMessage());
        }
    }

    @Override
    public void startView() {
        listarPeixes();
    }
}
