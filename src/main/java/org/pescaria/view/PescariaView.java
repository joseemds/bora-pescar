package org.pescaria.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.pescaria.entity.Peixe;
import org.pescaria.entity.PeixeUnico;
import org.pescaria.entity.Pescaria;
import org.pescaria.exception.DAOException;
import org.pescaria.service.PescariaService;
import org.pescaria.service.AuthService;
import org.pescaria.service.PeixeService;

public class PescariaView implements View {
    private PescariaService pescariaService = new PescariaService();
    private PeixeService peixeService = new PeixeService();
    private Scanner scanner = new Scanner(System.in);
    private PeixeView peixeView = new PeixeView();

    @Override
    public void startView() {
        System.out.println("Menu de Pesca");
        System.out.println("1 - Cadastrar nova pescaria");
        System.out.println("2 - Ver minhas pescas");
        System.out.println("3 - Ver todos os peixes que já peguei");
        System.out.println("0 - Voltar");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                inserirDadosPescaria();
            case 2:
                listarPescarias();
                break;
            case 3:
                listarPeixesMenu();
                break;

            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void inserirDadosPescaria() {
        System.out.println("Vamos cadastrar uma nova pescaria.");
        System.out.print("Digite a data (dd/mm/yyyy): ");
        String dateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        System.out.print("Digite o local: ");
        String local = scanner.nextLine();

        // O usuário irá inserir quantos peixes pegou, rodamos a quantidade num for e
        // inserimos os peixes num array
        System.out.println("Quantos peixes você pegou? ");
        int qtdPeixes = scanner.nextInt();
        scanner.nextLine();
        List<PeixeUnico> peixes = new ArrayList<>();

        peixeView.startView();

        for (int i = 0; i < qtdPeixes; i++) {

            System.out.print("Digite o número do " + (i + 1) + "º peixe que você pegou: ");
            int idPeixe = scanner.nextInt();
            Peixe peixeEscolhido = null;
            try {
                peixeEscolhido = peixeService.encontrarPorId(idPeixe);
            } catch (DAOException e) {
                e.printStackTrace();
            }

            scanner.nextLine();
            System.out.print("Digite o peso do peixe (g): ");
            double peso = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Digite o tamanho do peixe (cm): ");
            double tamanho = scanner.nextDouble();
            scanner.nextLine();
            PeixeUnico peixeUnico = new PeixeUnico(peixeEscolhido, peso, tamanho);
            peixes.add(peixeUnico);
        }

        Pescaria pescaria = new Pescaria(date, local, peixes, AuthService.getAutenticado());
        cadastrarPescaria(pescaria);

    }

    public void cadastrarPescaria(Pescaria pescaria) {
        try {
            pescariaService.cadastrarPescaria(pescaria);
            System.out.println("Pescaria cadastrada com sucesso.");
        } catch (DAOException e) {
            System.err.println("Erro ao cadastrar pescaria: " + e.getMessage());
        }
    }

    public void listarPescarias() {
        try {
            List<Pescaria> pescarias = pescariaService.listarPescariaUsuario();
            System.out.println("Lista de Pescarias:");
            for (Pescaria pescaria : pescarias) {
                System.out.println("ID: " + pescaria.getId() + ", Data: " + pescaria.getData() + ", Local: "
                        + pescaria.getLocal());
                System.out.println("Peixes pescados:");
                List<PeixeUnico> listaPeixes = pescaria.getPeixes();
                for (PeixeUnico peixe : listaPeixes) {
                    System.out.println(peixe);
                }
            }
        } catch (DAOException e) {
            System.err.println("Erro ao listar pescarias: " + e.getMessage());
        }
    }

    public void listarPeixesMenu() {
        System.out.println("================================");
        System.out.println("1 - Listar por tamanho");
        System.out.println("2 - Listar por peso");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                listarPeixesComFiltro("tamanho");
                break;
            case 2:
                listarPeixesComFiltro("peso");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void listarPeixesComFiltro(String filtro) {
        if (filtro == "tamanho") {
            System.out.println("filtro 1");
        } else if (filtro == "peso") {
            System.out.println("filtro 2");
        }
    }
}
