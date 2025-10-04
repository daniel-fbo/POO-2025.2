package SISTEMA;
import java.util.*;
import java.io.*;

public class SistemaCadastro implements TEXTO {
    private Scanner input = new Scanner(System.in);
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
        System.out.print("Endereço: ");
        String endereco = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("CPF: ");
        String cpf = input.nextLine();

        Cliente cliente = new Cliente(nome, endereco, telefone, email, cpf);

        try (BufferedWriter preencher = new BufferedWriter(new FileWriter(CLIENTES, true))) {
            preencher.write(cliente.fichaCliente());
            preencher.newLine(); //PulaLinha
            System.out.println("Cliente cadastrado.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o cadastro do cliente: " + e.getMessage());
        }
    }

    private static void cadastrarMedico () {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Endereço: ");
        String endereco = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("CPF: ");
        String cpf = input.nextLine();

        Cliente cliente = new Cliente(nome, endereco, telefone, email, cpf);

        try (BufferedWriter preencher = new BufferedWriter(new FileWriter(CLIENTES, true))) {
            preencher.write(cliente.fichaCliente());
            preencher.newLine(); //PulaLinha
            System.out.println("Cliente cadastrado.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o cadastro do cliente: " + e.getMessage());
        }
    }
    }


