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
                case "2" -> tecla2.abrirMenu();
                case "3" -> tecla3.abrirMenu();
                case "4" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("3"));
    }


    public void cadastrarPaciente() {
        System.out.println("oi");

    }

    }


