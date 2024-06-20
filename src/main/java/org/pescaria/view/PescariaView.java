package org.pescaria.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    @Override
    public void startView() {
        System.out.println(ANSI_BLUE + "Menu de Pesca" + ANSI_RESET);
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

        LocalDate date = null;
        while (date == null) {
            System.out.print("Digite a data (dd/mm/yyyy): ");
            String dateStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                date = LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Por favor, tente novamente.");
            }
        }
        System.out.print("Digite o local: ");
        String local = scanner.nextLine();

        // O usuário irá inserir quantos peixes pegou, rodamos a quantidade num for e
        // inserimos os peixes num array

        int qtdPeixes = 0;
        while (qtdPeixes < 1) {
            System.out.print("Quantos peixes você pegou? ");
            try {
                qtdPeixes = scanner.nextInt();
                if (qtdPeixes < 1) {
                    System.out.println("A quantidade precisa ser de pelo menos 1.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Insira um número.");
                scanner.next();
            }
        }
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
        System.out.println("3 - Listar por data");
        System.out.println("4 - Listar agrupado");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                listarPeixesPorTamanho();
                break;
            case 2:
                listarPeixesPorPeso();
                break;
            case 3:
                listarPescariasPorData();
                break;
            case 4:
                listarPescariasAgrupado();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private void listarPeixesPorTamanho() {
        try {
            var peixesPorTamanho = pescariaService.listarPescariaUsuario().stream()
                    .flatMap(pescaria -> pescaria.getPeixes().stream())
                    .sorted((p1, p2) -> Double.compare(p2.getTamanho(), p1.getTamanho())).collect(Collectors.toList());

            for (var peixe : peixesPorTamanho) {
                System.out.println(peixe);
            }

        } catch (DAOException e) {
            System.err.println("Erro ao listar pescarias: " + e.getMessage());
        }
    }

    private void listarPeixesPorPeso() {
        try {

            var peixesPorPeso = pescariaService.listarPescariaUsuario().stream()
                    .flatMap(pescaria -> pescaria.getPeixes().stream())
                    .sorted((p1, p2) -> Double.compare(p2.getPeso(), p1.getPeso())).collect(Collectors.toList());

            for (var peixe : peixesPorPeso) {
                System.out.println(peixe);
            }
        } catch (DAOException e) {

            System.err.println("Erro ao listar pescarias: " + e.getMessage());
        }

    }

    private void listarPescariasPorData() {
        try {

            System.out.print("Digite a data (dd/mm/yyyy): ");
            String dateStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            var pescariasFiltradas = pescariaService.listarPescariaUsuario().stream()
                    .filter(pescaria -> pescaria.getData().isAfter(date) || pescaria.getData().isEqual(date))
                    .flatMap(pescaria -> pescaria.getPeixes().stream()).collect(Collectors.toList());

            for (var pescaria : pescariasFiltradas) {
                System.out.println(pescaria);
            }

        } catch (DAOException e) {
            System.err.println("Erro ao listar pescarias: " + e.getMessage());

        }

    }

    private void listarPescariasAgrupado() {
        try {
            var agrupados = pescariaService.listarPescariaUsuario().stream()
                    .flatMap(pescaria -> pescaria.getPeixes().stream())
                    .collect(Collectors.groupingBy(peixe -> peixe.getPeixe().getEspecie(), Collectors.counting()));

            for (var peixes : agrupados.entrySet()) {
                System.out.printf("%s : %d\n", peixes.getKey(), peixes.getValue());
            }

        } catch (DAOException e) {
            System.err.println("Erro ao listar pescarias: " + e.getMessage());
        }
    }
}
