import REPOSITORIOS.RCONSULTA.*;
import REPOSITORIOS.RESPECIALIDADE.*;
import REPOSITORIOS.RINTERNACAO.*;
import REPOSITORIOS.RMEDICO.*;
import REPOSITORIOS.RPACIENTE.*;
import REPOSITORIOS.RPLANODESAUDE.*;
import SISTEMAS.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        REP_PACIENTE rPaciente = new REP_PACIENTE_RAM();
        REP_MEDICO rMedico = new REP_MEDICO_RAM();
        REP_PLANO rPlano = new REP_PLANO_RAM();
        REP_CONSULTA rConsulta = new REP_CONSULTA_RAM();
        REP_INTERNACAO rInternacao = new REP_INTERNACAO_RAM();
        REP_ESPECIALIDADE rEspecialidade = new REP_ESPECIALIDADE_RAM();

        Scanner input = new Scanner(System.in);
        SistemaCadastro tecla1 = new SistemaCadastro(input, rPaciente, rMedico, rPlano, rEspecialidade);
        SistemaInternacao tecla2 = new SistemaInternacao();
        SistemaAgendamento tecla3 = new SistemaAgendamento();
        RegistrosGerais tecla4 = new RegistrosGerais();
        String tecla;

        do {
            System.out.println("\n==== HOSPITAL DIAC ====");
            System.out.println("1 - Sistema de Cadastro");
            System.out.println("2 - Sistema de Internação");
            System.out.println("3 - Agendar nova consulta");
            System.out.println("4 - Buscar nos registros");
            System.out.println("5 - Sair do sistema.");
            System.out.print("Digite a opção desejada: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> tecla1.abrirMenu();
                case "2" -> tecla2.abrirMenu();
                case "3" -> tecla3.abrirMenu();
                case "4" -> tecla4.abrirMenu();
                case "5" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("5"));
    }
}