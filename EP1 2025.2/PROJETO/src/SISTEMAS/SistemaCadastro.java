package SISTEMAS;
import ENTIDADES.PACIENTE.*;
import ENTIDADES.MEDICO.*;
import ENTIDADES.PLANODESAUDE.*;
import ENTIDADES.MEDICO.Especialidades;
import REPOSITORIOS.RESPECIALIDADE.*;
import REPOSITORIOS.RMEDICO.*;
import REPOSITORIOS.RPACIENTE.*;
import REPOSITORIOS.RPLANODESAUDE.*;
import java.util.List;
import java.util.Scanner;



public class SistemaCadastro implements Menu {
    private final Scanner input;
    private final REP_PACIENTE rPaciente;
    private final REP_MEDICO rMedico;
    private final REP_PLANO rPlano;
    private final REP_ESPECIALIDADE rEspecialidade;

    public SistemaCadastro(Scanner input, REP_PACIENTE rPaciente, REP_MEDICO rMedico,
                           REP_PLANO rPlano, REP_ESPECIALIDADE rEspecialidade){

        this.input = input;
        this.rPaciente = rPaciente;
        this.rMedico = rMedico;
        this.rPlano = rPlano;
        this.rEspecialidade = rEspecialidade;
    }

    @Override
    public void abrirMenu() {
        String tecla;
        do {
            System.out.println("\n==== SISTEMA DE CADASTRO ====");
            System.out.println("1 - Cadastro de paciente.");
            System.out.println("2 - Cadastro de médico.");
            System.out.println("2 - Cadastro de especialidade médica.");
            System.out.println("4 - Cadastro de plano de saúde.");
            System.out.println("5 - Voltar ao menu principal: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> cadastrarPaciente();
                case "2" -> cadastrarMedico();
                case "3" -> cadastrarPlano();
                case "4" -> cadastrarEspecialidade();
                case "5" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("5"));
    }

    public void cadastrarPaciente () {
        System.out.println("\n--- Cadastro de Paciente ---");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CPF: ");
        String cpf = input.nextLine();
        System.out.print("Idade: ");
        short idade = input.nextShort();

        EstadoPaciente[] listaEstados = EstadoPaciente.values();
        System.out.println("Estado do paciente na triagem:");
        for(int i = 0; i < listaEstados.length; i++){
            System.out.println((i+1) + " - " + listaEstados[i] + ".");
        }

        EstadoPaciente estado = null;
        while (estado == null){
            try{
                System.out.println("Digite a opção correspondente:");
                short opcao = input.nextShort();
                if (opcao < 1 || opcao > listaEstados.length){
                    throw new IllegalArgumentException("Opção inválida.");
                }
                estado = listaEstados[opcao - 1];
            } catch (Exception e){
                System.out.println("Digite um número entre 1 e "+ listaEstados.length + ".");
                input.nextLine(); //LimpaBuffer
            }
        }

        short temPlano = 0;
        PlanoDeSaude planoSelecionado = null;
        System.out.println("O paciente possui plano de saúde?\n 1- Sim.\n 2-Não.");
        try{
            System.out.println("Digite a opção correspondente:");
            short opcao = input.nextShort();
            if (opcao < 1 || opcao > 2){
                throw new IllegalArgumentException("Opção inválida.");
            }
            temPlano = opcao;
        } catch (Exception e){
            System.out.println("Digite um número entre 1 e 2.");
            input.nextLine(); //LimpaBuffer
        }

        if(temPlano==1) {
            List<PlanoDeSaude> planosDisponiveis = rPlano.listarPlanos();
            if (planosDisponiveis.isEmpty()) {
                System.out.println("Nenhum plano de saúde cadastrado. Paciente será cadastrado sem plano.");
            } else {
                System.out.println("Planos disponíveis:");
                for (int i = 0; i < planosDisponiveis.size(); i++) {
                    System.out.println(i + 1 + "- " + planosDisponiveis.get(i).getNome() + "\n");
                }
            }
            while (planoSelecionado == null) {
                try {
                    short opcao;
                    System.out.println("Digite a opção correspondente: ");
                    opcao = input.nextShort();
                    if (opcao < 1 || opcao > planosDisponiveis.size()) {
                        throw new IllegalArgumentException("Opção inválida.");
                    }
                    planoSelecionado = planosDisponiveis.get(opcao - 1);
                } catch (Exception e) {
                    System.out.println("Digite uma opção entre 1 e " + planosDisponiveis.size());
                    input.nextLine();
                }
            }
        }

        if (idade <= 12) {
            Paciente paciente = new Paciente_Crianca(nome, cpf, idade, estado, planoSelecionado);
            rPaciente.salvarPaciente(paciente);
        } else if (idade >= 60){
            Paciente paciente = new Paciente_Idoso(nome, cpf, idade, estado, planoSelecionado);
            rPaciente.salvarPaciente(paciente);
        } else {
            Paciente paciente = new Paciente(nome, cpf, idade, estado, planoSelecionado);
            rPaciente.salvarPaciente(paciente);
        }
    }

    public void cadastrarMedico() {
        System.out.println("\n--- Cadastro de Médico ---");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CRM: ");
        String crm = input.nextLine();
        System.out.print("Custo da consulta: ");
        double custoConsulta = input.nextShort();

        List<Especialidades> listaEspecialidades = rEspecialidade.listarEspecialidades();
        Especialidades especialidade = null;
        System.out.println("Especialidade do médico:");
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
        Medico medico = new Medico(nome, crm, custoConsulta, especialidade);

        rMedico.salvarMedico(medico);

    }

    public void cadastrarPlano(){
        System.out.println("\n--- Cadastro de Plano ---");
        int opcao;
        System.out.print("Nome: ");
        String nome = input.nextLine();

        while(true){
            try{
                System.out.println("Desconto aplicado a consultas: ");
                System.out.println("1- 10%");
                System.out.println("2- 15%");
                System.out.println("3- 25%");
                System.out.println("Digite a opção correspondente: ");
                opcao = input.nextInt();

                if (opcao < 1 || opcao > 3){
                    throw new IllegalArgumentException("Opção inválida");
                }
                break;

            } catch (Exception e){
                System.out.println("Digite um número entre 1 e 3");
                input.nextLine();
            }
        }

        float descontoConsulta;
        switch (opcao){
            case 1 -> descontoConsulta = 0.90f;
            case 2 -> descontoConsulta = 0.85f;
            case 3 -> descontoConsulta = 0.75f;
            default -> descontoConsulta = 1f;
        }

        while(true){
            try{
                //Desconto aplicado ao custo diário
                System.out.println("Desconto aplicado aos custos de internação: ");
                System.out.println("1- 25%");
                System.out.println("2- 35%");
                System.out.println("3- 50%");
                System.out.println("Digite a opção correspondente: ");
                opcao = input.nextInt();

                if (opcao < 1 || opcao > 3){
                    throw new IllegalArgumentException("Opção inválida");
                }
                break;

            } catch (Exception e){
                System.out.println("Digite um número entre 1 e 3");
                input.nextLine();
            }
        }

        float descontoInternacao;
        switch (opcao){
            case 1 -> descontoInternacao = 0.75f;
            case 2 -> descontoInternacao = 0.65f;
            case 3 -> descontoInternacao = 0.5f;
            default -> descontoInternacao = 1f;
        }

        List<Especialidades> listaEspecialidades = rEspecialidade.listarEspecialidades();
        Especialidades especialidade = null;
        System.out.println("Especialidade com desconto especial:");
        for(int i = 0; i < listaEspecialidades.size(); i++){
            System.out.println((i+1) + " - " + listaEspecialidades.get(i) + ".");
        }

        while (especialidade == null){
            try{
                System.out.println("Digite a opção correspondente:");
                opcao = input.nextShort();
                if (opcao < 1 || opcao > listaEspecialidades.size()){
                    throw new IllegalArgumentException("Opção inválida.");
                }
                especialidade = listaEspecialidades.get(opcao - 1);
            } catch (Exception e){
                System.out.println("Digite um número entre 1 e "+ listaEspecialidades.size() + ".");
                input.nextLine(); //LimpaBuffer
            }
        }

        PlanoDeSaude plano = new PlanoDeSaude(nome, descontoConsulta, descontoInternacao, especialidade);

        rPlano.salvarPlano(plano);
    }

    public void cadastrarEspecialidade(){
        System.out.println("\n--- Cadastro de Especialização ---");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        Especialidades novaEspecialidade = new Especialidades(nome);
        rEspecialidade.salvarEspecialidade(novaEspecialidade);
    }

}


