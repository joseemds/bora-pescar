package org.pescaria.view;

import java.util.Scanner;

import org.pescaria.entity.Peixe;

public class MainView implements View {
	private LoginView loginView = new LoginView();
	private PescariaView pescariaView = new PescariaView();
	private CadastroPeixeView cadastroPeixeView = new CadastroPeixeView();
	private Scanner scanner = new Scanner(System.in);

	@Override
	public void startView() {
		System.out.println("Bem-vindo ao Bora Pescaria");
		System.out.println("1 - Login");
		System.out.println("2 - Cadastrar Peixe");
		System.out.println("3 - Cadastrar Pescaria");
		System.out.println("4 - Listar Pescarias");
		System.out.println("5 - Listar Peixes");
		System.out.println("0 - Sair");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		switch (opcao) {
			case 1:
				loginView.startView();
				break;
			case 2:
				cadastroPeixeView.startView();
				break;
			case 3:
				pescariaView.startView();
				break;
			case 4:
				pescariaView.listarPescarias();
				break;
			case 5:
				cadastroPeixeView.startView();
				break;
			case 0:
				System.out.println("Até mais!");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
		}

		if (opcao != 0) {
			startView();
		}
	}
}
