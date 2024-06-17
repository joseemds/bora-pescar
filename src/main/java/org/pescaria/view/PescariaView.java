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
import org.pescaria.service.PeixeService;

public class PescariaView implements View {
    private PescariaService pescariaService = new PescariaService();
    private PeixeService peixeService = new PeixeService();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void startView() {
        System.out.println("Vamos cadastrar uma nova pescaria.");
        System.out.print("Digite a data (dd/mm/yyyy): ");
        String dateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        System.out.print("Digite o local: ");
        String local = scanner.nextLine();

        // O usuário irá inserir quantos peixes pegou, rodamos a quantidade num for e
        // inserimos os peixes num array
        System.out.print("Quantos peixes você pegou?");
        int qtdPeixes = scanner.nextInt();
        scanner.nextLine();
        List<PeixeUnico> peixes = new ArrayList<>();

        System.out.println("Lista de peixes: ");
        try {
            peixeService.listarPeixes();
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < qtdPeixes; i++) {

            System.out.print("Digite o número do " + (i + 1) + "º peixe que você pegou: ");
            int idPeixe = scanner.nextInt();
            Peixe peixeEscolhido = null;
            try {
                peixeEscolhido = peixeService.encontrarPorId(idPeixe);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            scanner.nextLine();
            System.out.print("Digite o peso do peixe: ");
            double peso = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Digite o tamanho do peixe: ");
            double tamanho = scanner.nextDouble();
            scanner.nextLine();
            PeixeUnico peixeUnico = new PeixeUnico(peixeEscolhido, peso, tamanho);
            peixes.add(peixeUnico);
        }

        Pescaria pescaria = new Pescaria(date, local, peixes);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPescarias'");
    }
}
