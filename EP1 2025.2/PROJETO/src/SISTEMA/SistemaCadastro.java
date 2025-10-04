package SISTEMA;
import java.util.*;
import java.io.*;
import ENTIDADES.PACIENTE.*;
import ENTIDADES.MEDICO.*;
public class SistemaCadastro implements TEXTO {
    private static Scanner input = new Scanner(System.in);
    private String tecla;

    @Override
    public void abrirMenu() {
        do {
            System.out.println("\n==== SISTEMA DE CADASTRO ====");
            System.out.println("1 - Cadastro de paciente.");
            System.out.println("2 - Cadastro de médico.");
            System.out.println("3 - Voltar ao menu principal: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> cadastrarPaciente();
                case "2" -> cadastrarMedico();
                case "3" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("3"));
    }


    public void cadastrarPaciente () {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CPF: ");
        String cpf = input.nextLine();
        System.out.print("Idade: ");
        short idade = input.nextShort();
        if (idade <= 12) {
            Paciente paciente = new Paciente(nome, cpf, idade);
        }

        try (BufferedWriter preencher = new BufferedWriter(new FileWriter(CLIENTES, true))) {
            preencher.write(cliente.fichaCliente());
            preencher.newLine(); //PulaLinha
            System.out.println("Cliente cadastrado.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o cadastro do cliente: " + e.getMessage());
        }
    }

    public void cadastrarMedico() {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CRM: ");
        short cpf = input.nextShort();
        System.out.print("Custo da consulta: ");
        double idade = input.nextShort();


        Paciente paciente = new Paciente(nome, cpf, idade);

        try (BufferedWriter preencher = new BufferedWriter(new FileWriter(CLIENTES, true))) {
            preencher.write(cliente.fichaCliente());
            preencher.newLine(); //PulaLinha
            System.out.println("Cliente cadastrado.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o cadastro do cliente: " + e.getMessage());
        }
    }
    }


