package org.pescaria.view;

import org.pescaria.entity.Usuario;
import org.pescaria.service.AuthService;
import org.pescaria.service.UsuarioService;
import org.pescaria.exception.DAOException;

import java.util.Scanner;

public class LoginView implements View {
    private UsuarioService usuarioService = new UsuarioService();
    private Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void startView() {
        System.out.print("Insira o seu usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Insira sua senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = realizarLogin(nome, senha);

        if (usuario != null) {
            AuthService.login(usuario);
            System.out.println("Login realizado com sucesso. Bem-vindo, " + usuario.getNome());
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }

    public Usuario realizarLogin(String nome, String senha) {
        try {
            return usuarioService.realizarLogin(nome, senha);
        } catch (DAOException e) {
            System.err.println(ANSI_RED + "Erro ao realizar login: " + e.getMessage() + ANSI_RESET);

            System.err.println();
            return null;
        }
    }
}
