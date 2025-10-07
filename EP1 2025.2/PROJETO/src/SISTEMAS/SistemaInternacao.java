package SISTEMAS;
import ENTIDADES.PACIENTE.Paciente;
import PROCESSOS.INTERNACAO.*;
import REPOSITORIOS.RPACIENTE.*;
import REPOSITORIOS.RINTERNACAO.*;
import java.util.Optional;
import java.util.Scanner;

public class SistemaInternacao implements Menu {
    private final Scanner input;
    private final REP_PACIENTE rPaciente;
    private final GerenciadorLeitos gerenciadorLeitos;
    private final REP_INTERNACAO rInternacao;

    public SistemaInternacao(Scanner input, REP_PACIENTE pRepo, GerenciadorLeitos gLeitos, REP_INTERNACAO iRepo) {
        this.input = input;
        this.rPaciente = pRepo;
        this.gerenciadorLeitos = gLeitos;
        this.rInternacao = iRepo;
    }


    @Override
    public void abrirMenu(){
        String tecla;
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
