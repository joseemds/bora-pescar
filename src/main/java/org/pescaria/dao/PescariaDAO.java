package org.pescaria.dao;

import org.pescaria.database.Database;
import org.pescaria.entity.Pescaria;
import org.pescaria.exception.DAOException;
import org.pescaria.exception.DatabaseException;
import org.pescaria.exception.EntityNotFoundException;

import java.util.List;

public class PescariaDAO {
    private Database database = Database.getInstance();

    public void salvarPescaria(Pescaria pescaria) throws DAOException {
        try {
            database.save(pescaria);
        } catch (DatabaseException e) {
            throw new DAOException("Error saving Pescaria: " + e.getMessage());
        }
    }

    public List<Pescaria> listarTodas() throws DAOException {
        try {
            return database.findAll(Pescaria.class);
        } catch (DatabaseException e) {
            throw new DAOException("Error listing Pescarias: " + e.getMessage());
        }
    }

    public Pescaria obterPorId(int id) throws DAOException {
        try {
            return database.findById(Pescaria.class, id);
        } catch (EntityNotFoundException e) {
            throw new DAOException("Pescaria not found: " + e.getMessage());
        }
    }
}
