package org.pescaria.view;

import org.pescaria.entity.Usuario;
import org.pescaria.service.UsuarioService;
import org.pescaria.exception.DAOException;

import java.util.Scanner;

public class LoginView implements View {
    private UsuarioService usuarioService = new UsuarioService();
    private Scanner scanner = new Scanner(System.in);
    private Usuario usuarioLogado;

    @Override
    public void startView() {
        System.out.print("Insira o seu usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Insira sua senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = realizarLogin(nome, senha);

        if (usuario != null) {
            usuarioLogado = usuario;
            System.out.println("Login realizado com sucesso. Bem-vindo, " + usuario.getNome());
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }

    public Usuario realizarLogin(String nome, String senha) {
        try {
            return usuarioService.realizarLogin(nome, senha);
        } catch (DAOException e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
            return null;
        }
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
