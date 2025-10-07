package SISTEMAS;
import REPOSITORIOS.RCONSULTA.*;
import REPOSITORIOS.RESPECIALIDADE.*;
import REPOSITORIOS.RINTERNACAO.*;
import REPOSITORIOS.RMEDICO.*;
import REPOSITORIOS.RPACIENTE.*;
import REPOSITORIOS.RPLANODESAUDE.*;
import java.util.Scanner;

public class RegistrosGerais implements Menu {
    REP_PACIENTE rPaciente;
    REP_MEDICO rMedico;
    REP_PLANO rPlano;
    REP_CONSULTA rConsulta;
    REP_INTERNACAO rInternacao;
    REP_ESPECIALIDADE rEspecialidade;
    Scanner input;

    public RegistrosGerais(Scanner input,REP_PACIENTE rPaciente, REP_MEDICO rMedico, REP_PLANO rPlano, REP_CONSULTA rConsulta,REP_INTERNACAO rInternacao,REP_ESPECIALIDADE rEspecialidade){
        this.input = input;
        this.rPaciente = rPaciente;
        this.rMedico = rMedico;
        this.rPlano = rPlano;
        this.rConsulta = rConsulta;
        this.rInternacao = rInternacao;
        this.rEspecialidade = rEspecialidade;
    }


    @Override
    public void abrirMenu() {
        String tecla;
        do {
            System.out.println("\n==== SISTEMA DE REGISTRO ====");
            System.out.println("1 - Registros de pacientes.");
            System.out.println("2 - Registros de médicos.");
            System.out.println("3 - Registros de planos de saúde.");
            System.out.println("4 - Registros de consultas.");
            System.out.println("5 - Registros de internações.");
            System.out.println("6 - Voltar ao menu principal: ");
            tecla = input.nextLine();

            try{
                switch (tecla) {
                    case "1" -> rPaciente.listarPacientes();
                    case "2" -> rMedico.listarMedicos();
                    case "3" -> rPlano.listarPlanos();
                    case "4" -> rConsulta.listarConsultas();
                    case "5" -> rInternacao.listarInternacoes();
                    case "6" -> System.out.println("SISTEMA FECHADO");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        } while (!tecla.equals("6"));
    }

}
