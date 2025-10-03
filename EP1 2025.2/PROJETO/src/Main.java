import SISTEMA.*;
import ENTIDADES_CONCRETAS.*;
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SistemaCadastro tecla1 = new SistemaCadastro;
        SistemaInternacao tecla2 = new SistemaInternacao;
        SistemaAgendamento tecla3 = new SistemaAgendamento;
        do {
            System.out.println("\n==== HOSPITAL DIAC ====");
            System.out.println("1 - Sistema de Cadastramento");
            System.out.println("2 - Sistema de Internação");
            System.out.println("3 - Agendar nova consulta");
            System.out.println("4 - Sair do sistema.");
            System.out.print("Digite a opção desejada: ");
            int tecla = input.nextString();
            input.nextLine(); // LimpaBuffer
            switch (tecla) {
                case 1 -> tecla1.abirMenu();
                case 2 -> tecla2.abirMenu();
                case 3 -> tecla3.abirMenu();
                case 4 -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (tecla != 4);
    }


}