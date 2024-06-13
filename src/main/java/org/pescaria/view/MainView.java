package org.pescaria.view;

import java.util.Scanner;
import org.pescaria.entity.TipoUsuario;
import org.pescaria.entity.Usuario;

public class MainView implements View {
    private LoginView loginView = new LoginView();
    private PescariaView pescariaView = new PescariaView();
    private CadastroPeixeView cadastroPeixeView;
    private CadastroUsuarioView cadastroUsuarioView = new CadastroUsuarioView();
    private Scanner scanner = new Scanner(System.in);
    private Usuario usuarioLogado = null;

    @Override
    public void startView() {
        while (true) {
            if (usuarioLogado == null) {
                showInitialMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showInitialMenu() {
        System.out.println("================================");
        System.out.println("Bem-vindo ao Bora Pescaria");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastrar Usuário");
        System.out.println("0 - Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                loginView.startView();
                usuarioLogado = loginView.getUsuarioLogado();
                break;
            case 2:
                cadastroUsuarioView.startView();
                break;
            case 0:
                System.out.println("Até mais!");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private void showMainMenu() {
        System.out.println("================================");
        System.out.println("Bem-vindo ao Bora Pescaria");
        System.out.println("1 - Cadastrar Peixe");
        System.out.println("2 - Cadastrar Pescaria");
        System.out.println("3 - Listar Pescarias");
        System.out.println("4 - Listar Peixes");
        System.out.println("5 - Logout");
        System.out.println("0 - Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                if (usuarioLogado != null && usuarioLogado.getTipo() == TipoUsuario.ADMIN) {
                    cadastroPeixeView = new CadastroPeixeView(usuarioLogado);
                    cadastroPeixeView.startView();
                } else {
                    System.out.println("Apenas administradores podem cadastrar peixes.");
                }
                break;
            case 2:
                pescariaView.startView();
                break;
            case 3:
                pescariaView.listarPescarias();
                break;
            case 4:
                pescariaView.startView();
                break;
            case 5:
                usuarioLogado = null;
                System.out.println("Logout realizado com sucesso.");
                break;
            case 0:
                System.out.println("Até mais!");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
