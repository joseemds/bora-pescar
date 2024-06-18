package org.pescaria.dao;

import org.pescaria.database.Database;
import org.pescaria.entity.Peixe;
import org.pescaria.exception.DAOException;
import org.pescaria.exception.DatabaseException;

import java.util.List;

public class PeixeDAO {
    private Database database = Database.getInstance();

    public void salvarPeixe(Peixe peixe) throws DAOException {
        try {
            database.save(peixe);
        } catch (DatabaseException e) {
            throw new DAOException("Erro ao salvar peixe: " + e.getMessage());
        }
    }

    public List<Peixe> listarTodos() throws DAOException {
        try {
            // Printa a lista de peixes assim: "id - nome"
            return database.findAll(Peixe.class);
        } catch (DatabaseException e) {
            throw new DAOException("Erro ao listar peixes: " + e.getMessage());
        }
    }

    public Peixe encontrarPorId(int id) throws DAOException {
        try {
            return database.findById(Peixe.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Erro ao encontrar peixe por id: " + e.getMessage());
        }
    }
}
