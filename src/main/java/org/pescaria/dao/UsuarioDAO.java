package org.pescaria.dao;

import org.pescaria.database.Database;
import org.pescaria.entity.Usuario;
import org.pescaria.exception.DAOException;
import org.pescaria.exception.DatabaseException;
import org.pescaria.exception.EntityNotFoundException;

public class UsuarioDAO {
    private Database database = Database.getInstance();

    public void salvarUsuario(Usuario usuario) throws DAOException {
        try {
            database.save(usuario);
        } catch (DatabaseException e) {
            throw new DAOException("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public Usuario login(String nome, String senha) throws DAOException {
        try {
            return database.findAll(Usuario.class).stream()
                    .filter(usuario -> usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)).findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        } catch (DatabaseException e) {
            throw new DAOException("Error fetching user: " + e.getMessage());
        }
    }
}
