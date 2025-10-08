package SISTEMAS;
import ENTIDADES.PACIENTE.Paciente;
import PROCESSOS.CONSULTAS.PacienteNaoCadastrado;
import REPOSITORIOS.RCONSULTA.*;
import REPOSITORIOS.RESPECIALIDADE.*;
import REPOSITORIOS.RINTERNACAO.*;
import REPOSITORIOS.RMEDICO.*;
import REPOSITORIOS.RPACIENTE.*;
import REPOSITORIOS.RPLANODESAUDE.*;

import java.util.Optional;
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
            System.out.println("4 - Buscar histórico de paciente.");
            System.out.println("5 - Voltar ao menu principal: ");
            tecla = input.nextLine();

            try{
                switch (tecla) {
                    case "1" -> rPaciente.imprimirPacientes();
                    case "2" -> rMedico.imprimirMedicos();
                    case "3" -> rPlano.imprimirPlanos();
                    case "4" -> buscarHistorico();
                    case "5" -> System.out.println("SISTEMA FECHADO");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        } while (!tecla.equals("5"));
    }

    public void buscarHistorico(){
        String nome;
        try {
            System.out.print("Digite o nome do paciente: ");
            String nomeTemp = input.nextLine();

            if (rPaciente.isntCadastrado(nomeTemp)) {
                throw new PacienteNaoCadastrado(nomeTemp);
            }
            nome = nomeTemp;

        } catch (PacienteNaoCadastrado e) {
            System.out.println(e.getMessage());
            return;
        }
        Optional<Paciente> oPaciente = rPaciente.buscarNome(nome);
        Paciente paciente = oPaciente.get();
        System.out.println("Paciente encontrado: " + paciente.getNome());

        System.out.println(paciente.getHistorico());

    }

}
