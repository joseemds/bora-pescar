package org.pescaria.view;

import java.util.Scanner;
import org.pescaria.entity.Usuario;
import org.pescaria.entity.TipoUsuario;
import org.pescaria.service.UsuarioService;
import org.pescaria.exception.DAOException;

public class CadastroUsuarioView implements View {
    private UsuarioService usuarioService = new UsuarioService();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void startView() {
        System.out.println("Cadastro de Usuário:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Usuário administrador? (S/N): ");
        String simOuNao = scanner.nextLine();

        while (!simOuNao.equalsIgnoreCase("S") && !simOuNao.equalsIgnoreCase("N")) {
            System.out.print("Usuário administrador? (S/N): ");
            simOuNao = scanner.nextLine();
        }

        TipoUsuario tipoUsuario = simOuNao.equalsIgnoreCase("S") ? TipoUsuario.ADMIN : TipoUsuario.COMUM;
        Usuario usuario = new Usuario(nome, senha, tipoUsuario);

        try {
            usuarioService.cadastrarUsuario(usuario);
            System.out.println("Usuário cadastrado com sucesso.");
        } catch (DAOException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
