package org.pescaria.service;

import org.pescaria.dao.PeixeDAO;
import org.pescaria.entity.Peixe;
import org.pescaria.exception.DAOException;

import java.util.List;

public class PeixeService {
    private PeixeDAO peixeDAO = new PeixeDAO();

    public void cadastrarPeixe(Peixe peixe) throws DAOException {
        peixeDAO.salvarPeixe(peixe);
    }

    public List<Peixe> listarPeixes() throws DAOException {
        return peixeDAO.listarTodos();
    }

    public Peixe encontrarPorId(int id) throws DAOException {
        return peixeDAO.encontrarPorId(id);
    }
}
