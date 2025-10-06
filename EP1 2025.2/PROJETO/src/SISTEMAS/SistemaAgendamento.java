package SISTEMAS;
import java.util.Scanner;

public class SistemaAgendamento implements Menu {
    private Scanner input = new Scanner(System.in);

    @Override
    public void abrirMenu(){
        String tecla;
        do {
            System.out.println("\n==== SISTEMA DE AGENDAMENTO ====");
            System.out.println("1 - Agendar consulta.");
            System.out.println("2 - Verificar consultas");
            System.out.print("Digite a opção desejada: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> agendarConsulta();
                case "2" -> verificarConsultas();
                case "3" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("3"));
    }

    public void agendarConsulta();
    public void verificarConsultas();


}
