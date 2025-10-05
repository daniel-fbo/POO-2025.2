package SISTEMA;
import java.util.*;

public class SistemaAgendamento implements TEXTO {
    Scanner input = new Scanner(System.in);
    String tecla;

    @Override
    public void abrirMenu(){
        do {
            System.out.println("\n==== SISTEMA DE AGENDAMENTO ====");
            System.out.println("1 - Cadastro de paciente.");
            System.out.println("2 - Cadastro de médico.");
            System.out.print("Digite a opção desejada: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> tecla1.abrirMenu();
                case "2" -> tecla2.abrirMenu();
                case "3" -> tecla3.abrirMenu();
                case "4" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("4"));
    }


    @Override
    public void cadastrar(){
    System.out.println("oi");

    }

}
