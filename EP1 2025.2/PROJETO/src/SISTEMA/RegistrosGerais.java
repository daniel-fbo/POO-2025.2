package SISTEMA;

import java.util.Scanner;

public class RegistrosGerais implements Menu {
    Scanner input = new Scanner(System.in);
    String tecla;

    @Override
    public void abrirMenu() {
        do {
            System.out.println("\n==== SISTEMA DE CADASTRO ====");
            System.out.println("1 - Registros de pacientes.");
            System.out.println("2 - Registros de médicos.");
            System.out.println("3 - Registros de planos de saúde.");
            System.out.println("4 - Registros de consultas.");
            System.out.println("5 - Registros de internações.");
            System.out.println("6 - Voltar ao menu principal: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> registrosPaciente();
                case "2" -> registrosMedico();
                case "3" -> registrosPlano();
                case "4" -> registrosConsulta();
                case "5" -> registrosInternacao();
                case "6" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("6"));
    }

    public void registrosPaciente();
    public void registrosMedico();
    public void registrosPlano();
    public void registrosConsulta();
    public void registrosInternacao();

}
