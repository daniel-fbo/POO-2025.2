package SISTEMAS;
import ENTIDADES.MEDICO.*;
import ENTIDADES.PACIENTE.*;
import ENTIDADES.PACIENTE.Paciente_Idoso;
import PROCESSOS.CONSULTAS.*;
import REPOSITORIOS.RCONSULTA.*;
import REPOSITORIOS.RESPECIALIDADE.*;
import REPOSITORIOS.RMEDICO.*;
import REPOSITORIOS.RPACIENTE.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SistemaConsultas implements Menu {
    private final Scanner input;
    private final REP_CONSULTA rConsulta;
    private final REP_ESPECIALIDADE rEspecialidade;
    private final REP_MEDICO rMedico;
    private final REP_PACIENTE rPaciente;

    public SistemaConsultas(Scanner input, REP_CONSULTA rConsulta, REP_ESPECIALIDADE rEspecialidade, REP_MEDICO rMedico, REP_PACIENTE rPaciente){
        this.input = input;
        this.rConsulta = rConsulta;
        this.rEspecialidade = rEspecialidade;
        this.rMedico = rMedico;
        this.rPaciente = rPaciente;
    }

    @Override
    public void abrirMenu(){
        String tecla;
        do {
            System.out.println("\n==== SISTEMA DE CONSULTAS ====");
            System.out.println("1 - Agendar consulta.");
            System.out.println("2 - Gerar relatório de consulta.");
            System.out.println("3 - Voltar ao menu principal.");
            System.out.print("Digite a opção desejada: ");
            tecla = input.nextLine();
            try {
                switch (tecla) {
                    case "1" -> agendarConsulta();
                    case "2" -> processarConsulta();
                    case "3" -> System.out.println("SISTEMA FECHADO");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Por favor, tente novamente.");
            }
        } while (!tecla.equals("3"));
    }

    public void agendarConsulta() {
        System.out.println("\n--- Agendamento de Consulta ---");


        System.out.print("Digite o nome do paciente: ");
        String nomePaciente = input.nextLine();

        if (rPaciente.isntCadastrado(nomePaciente)) {
            System.out.println("Paciente não cadastrado: " + nomePaciente);
            return;
        }

        Optional<Paciente> oPaciente = rPaciente.buscarNome(nomePaciente);
        if (oPaciente.isEmpty()) {
            System.out.println("Erro ao buscar paciente.");
            return;
        }
        Paciente paciente = oPaciente.get();
        System.out.println("Paciente encontrado: " + paciente.getNome());


        List<Especialidades> listaEspecialidades = rEspecialidade.listarEspecialidades();
        Especialidades especialidade = null;
        System.out.println("\nÁrea da consulta:");
        for (int i = 0; i < listaEspecialidades.size(); i++) {
            System.out.println((i + 1) + " - " + listaEspecialidades.get(i).getNome());
        }

        while (especialidade == null) {
            try {
                System.out.print("Digite a opção correspondente: ");
                short opcao = input.nextShort();
                input.nextLine();
                if (opcao < 1 || opcao > listaEspecialidades.size()) {
                    throw new IllegalArgumentException("Opção inválida.");
                }
                especialidade = listaEspecialidades.get(opcao - 1);
            } catch (Exception e) {
                System.out.println("Digite um número entre 1 e " + listaEspecialidades.size() + ".");
                input.nextLine(); // limpar buffer
            }
        }


        List<Medico> medicosDisponiveis = new ArrayList<>();
        for (Medico m : rMedico.listarMedicos()) {
            if (m.getEspecialidade().getNome().equalsIgnoreCase(especialidade.getNome())) {
                medicosDisponiveis.add(m);
            }
        }




        if (medicosDisponiveis.isEmpty()) {
            System.out.println("Nenhum médico disponível para essa especialidade.");
            return;
        }

        System.out.println("\nMédicos disponíveis:");
        for (int i = 0; i < medicosDisponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + medicosDisponiveis.get(i).getNome());
        }

        int escolha = 0;
        while (escolha < 1 || escolha > medicosDisponiveis.size()) {
            System.out.print("Escolha o médico pelo número: ");
            try {
                escolha = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números.");
                input.nextLine();
            }
        }

        Medico medico = medicosDisponiveis.get(escolha - 1);
        System.out.println("Médico selecionado: " + medico.getNome());


        double custoFinal = medico.getCustoConsulta();
        Consulta consulta = new Consulta(paciente, medico, especialidade, custoFinal, LocalDateTime.now());

        rConsulta.salvarConsulta(consulta);

        System.out.println("\n+++ Consulta agendada com sucesso! +++");
        System.out.println("Paciente: " + paciente.getNome());
        System.out.println("Médico: " + medico.getNome());
        System.out.println("Especialidade: " + especialidade.getNome());
        System.out.println("Custo: R$ " + String.format("%.2f", custoFinal));
    }

    public void processarConsulta(){
        System.out.println("\n--- Gerar Relatório de Consulta ---");
        System.out.print("Digite o código de identificação da consulta: ");
        int idCOnsulta = input.nextInt();
        input.nextLine();
        System.out.print("Digite a descrição médica da consulta: ");
        String descricao = input.nextLine();
        System.out.print("Digite os medicamentos prescritos: ");
        String medicamentos = input.nextLine();
        System.out.print("Digite os exames requeridos: ");
        String exames = input.nextLine();


        Diagnostico diagnostico = new Diagnostico(descricao, medicamentos, exames);

        Optional<Consulta> oConsulta = rConsulta.buscarIdConsulta(idCOnsulta);
        if (oConsulta.isEmpty()){
            System.out.println("Nenhuma internação ativa encontrada para o CPF: " + idCOnsulta);
            return;
        }
        Consulta consulta = oConsulta.get();
        consulta.setDiagnostico(diagnostico);
        RelatorioConsulta relatorio = consulta.registrarConsulta();
        consulta.getPaciente().getHistorico().adicionarConsultaHistorico(relatorio);

        if(consulta.paciente instanceof Paciente_Idoso){
            Paciente_Idoso idoso = (Paciente_Idoso) consulta.getPaciente();
            LocalDate hoje = LocalDate.now();
            idoso.setDataUltimaConsulta(hoje);
        }

        rConsulta.atualizarConsulta(consulta);
        System.out.println("\n+++ RELATÓRIO CONSULTA GERADO COM SUCESSO +++");
        System.out.println(relatorio);

    }

}
