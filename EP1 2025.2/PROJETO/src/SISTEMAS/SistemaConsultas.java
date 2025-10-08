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
import java.time.format.DateTimeFormatter;
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

    public void agendarConsulta(){
        double custoFinal;
        System.out.println("\n--- Agenda Consulta ---");

        //Paciente

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

        //Especialidade

        List<Especialidades> listaEspecialidades = rEspecialidade.listarEspecialidades();
        Especialidades especialidade = null;
        System.out.println("Área da consulta:");
        for(int i = 0; i < listaEspecialidades.size(); i++){
            System.out.println((i+1) + " - " + listaEspecialidades.get(i) + ".");
        }

        while (especialidade == null){
            try{
                System.out.println("Digite a opção correspondente:");
                short opcao = input.nextShort();
                if (opcao < 1 || opcao > listaEspecialidades.size()){
                    throw new IllegalArgumentException("Opção inválida.");
                }
                especialidade = listaEspecialidades.get(opcao-1);
            } catch (Exception e){
                System.out.println("Digite um número entre 1 e "+ listaEspecialidades.size() + ".");
                input.nextLine(); //LimpaBuffer
            }
        }

        //Médico

        Optional<Medico> possivelMedico = rMedico.buscarEspecialidade(especialidade);

        if (possivelMedico.isEmpty()){
            System.out.print("Não há medicos disponíveis para consultas nessa área.");
            return;
        }

        Medico medico = possivelMedico.get();
        System.out.println("\nMédico encontrado!\nDr(a): " + medico.getNome());

        //CustoFinal

        custoFinal = medico.getCustoConsulta();
        if (paciente instanceof Paciente_Idoso){
            custoFinal *= 1.15;
        } else if (paciente instanceof Paciente_Crianca){
            custoFinal *= 0.9;
        }

        if (paciente.plano.getEspecialidade() == especialidade){
            custoFinal *= 0.5;
        }

        custoFinal *= paciente.plano.descontoConsulta;

        //Horario.Data

        LocalDateTime horarioConsulta = null;
        while (horarioConsulta == null) {
            try {
                System.out.print("Digite a data e horário da consulta (formato: dd/MM/yyyy HH:mm): ");
                String data = input.nextLine();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                horarioConsulta = LocalDateTime.parse(data, formatador);
            } catch (Exception e) {
                System.out.println("Formato inválido.");
            }

        }

        try {
            if (!medico.getAgenda().isDisponivel(horarioConsulta)) {
                throw new HorarioIndisponivel(medico, horarioConsulta);
            }

            Consulta novaConsulta = new Consulta(paciente, medico, horarioConsulta, custoFinal);

            medico.getAgenda().adicionarConsulta(novaConsulta);

            System.out.println("\nConsulta agendada com sucesso!");
            System.out.println("Paciente: " + paciente.getNome());
            System.out.println("Médico: " + medico.getNome());
            System.out.println("Especialidade: " + especialidade);
            System.out.println("Data/Hora: " + horarioConsulta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        } catch (HorarioIndisponivel e) {
            System.out.println(e.getMessage());
        }


    }

    public void processarConsulta(){
        System.out.println("\n--- Gerar Relatório de Consulta ---");
        System.out.print("Digite o código de identificação da consulta: ");
        int idCOnsulta = input.nextInt();
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

        rConsulta.salvar(consulta);
        System.out.println("\n+++ ALTA REALIZADA COM SUCESSO +++");
        System.out.println(relatorio);

    }

}
