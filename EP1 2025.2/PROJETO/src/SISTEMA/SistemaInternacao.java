package SISTEMA;
import java.util.Scanner;

public class SistemaInternacao implements Menu {
    Scanner input = new Scanner(System.in);
    String tecla;

    @Override
    public void abrirMenu(){
        do {
            System.out.println("\n==== SISTEMA DE INTERNAÇÃO ====");
            System.out.println("1 - Internar paciente.");
            System.out.println("2 - Verificar situação dos leitos.");
            System.out.print("Digite a opção desejada: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> internarPaciente();
                case "2" -> verificarLeitos();
                case "3" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("3"));
    }

    public void internarPaciente();
    public void verificarLeitos();


}
