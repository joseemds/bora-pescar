package org.pescaria.view;

import org.pescaria.entity.Usuario;
import org.pescaria.service.UsuarioService;
import org.pescaria.exception.DAOException;

import java.util.Scanner;

public class LoginView implements View {
    private UsuarioService usuarioService = new UsuarioService();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void startView() {
        System.out.println("Login View:");
        // interagir com o usuário
    }

    public Usuario realizarLogin(String nome, String senha) {
        try {
            return usuarioService.realizarLogin(nome, senha);
        } catch (DAOException e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
            return null;
        }
    }
}
