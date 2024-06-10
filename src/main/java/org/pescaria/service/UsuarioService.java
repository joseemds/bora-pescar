package org.pescaria.service;

import org.pescaria.dao.UsuarioDAO;
import org.pescaria.entity.Usuario;
import org.pescaria.exception.DAOException;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario realizarLogin(String nome, String senha) throws DAOException {
        return usuarioDAO.obterPorNomeSenha(nome, senha);
    }
}
