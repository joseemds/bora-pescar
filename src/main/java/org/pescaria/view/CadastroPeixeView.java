package org.pescaria.view;

import java.util.Scanner;

import org.pescaria.entity.Peixe;
import org.pescaria.service.PeixeService;
import org.pescaria.exception.DAOException;

public class CadastroPeixeView implements View {
	private PeixeService peixeService = new PeixeService();

	@Override
	public void startView() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Cadastro de Peixe:");

		String especie = scanner.next();
		String descricao = scanner.next();
		Peixe peixe = new Peixe(especie, descricao);
		try {
			peixeService.cadastrarPeixe(peixe);
			System.out.println("Peixe cadastrado com sucesso.");
		} catch (DAOException e) {
			System.err.println("Erro ao cadastrar peixe: " + e.getMessage());
		} finally {
			scanner.close();
		}

	}

	public void cadastrarPeixe(Peixe peixe) {
		try {
			peixeService.cadastrarPeixe(peixe);
			System.out.println("Peixe cadastrado com sucesso.");
		} catch (DAOException e) {
			System.err.println("Erro ao cadastrar peixe: " + e.getMessage());
		}
	}
}
