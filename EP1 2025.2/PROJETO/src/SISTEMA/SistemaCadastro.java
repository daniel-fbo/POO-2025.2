package SISTEMA;
import ENTIDADES.PACIENTE.*;
import ENTIDADES.MEDICO.*;
import ENTIDADES.PLANODESAUDE.*;
import java.util.*;

public class SistemaCadastro implements Menu {
    Scanner input = new Scanner(System.in);
    String tecla;

    @Override
    public void abrirMenu() {
        do {
            System.out.println("\n==== SISTEMA DE CADASTRO ====");
            System.out.println("1 - Cadastro de paciente.");
            System.out.println("2 - Cadastro de médico.");
            System.out.println("3 - Cadastro de médico.");
            System.out.println("4 - Voltar ao menu principal: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> cadastrarPaciente();
                case "2" -> cadastrarMedico();
                case "3" -> cadastrarPlano();
                case "4" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("4"));
    }

    public void cadastrarPaciente () {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CPF: ");
        String cpf = input.nextLine();
        System.out.print("Idade: ");
        short idade = input.nextShort();

        //Array com todas as Especialidades
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

        if (idade <= 12) {
            Paciente_Crianca paciente = new Paciente_Crianca(nome, cpf, idade, estado);
        } else if (idade >= 60){
            Paciente_Idoso paciente = new Paciente_Idoso(nome, cpf, idade, estado);
        } else {
            Paciente paciente = new Paciente(nome, cpf, idade, estado);
        }
    }

    public void cadastrarMedico() {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CRM: ");
        short crm = input.nextShort();
        System.out.print("Custo da consulta: ");
        double custoConsulta = input.nextShort();

        //Array com todas as Especialidades
        Especialidades[] listaEspecialidades = Especialidades.values();

        System.out.println("Especialidade do médico:");
        for(int i = 0; i < listaEspecialidades.length; i++){
            System.out.println((i+1) + " - " + listaEspecialidades[i] + ".");
        }
        Especialidades especialidade = null;

        while (especialidade == null){
            try{
                System.out.println("Digite a opção correspondente:");
                short opcao = input.nextShort();
                if (opcao < 1 || opcao > listaEspecialidades.length){
                    throw new IllegalArgumentException("Opção inválida.");
                }
                especialidade = listaEspecialidades[opcao - 1];
            } catch (Exception e){
                System.out.println("Digite um número entre 1 e "+ listaEspecialidades.length + ".");
                input.nextLine(); //LimpaBuffer
            }
        }
        Medico medico = new Medico(nome, crm, custoConsulta, especialidade);

    }

    public void cadastrarPlano(){
        int opcao=0;
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

        PlanoDeSaude plano = new PlanoDeSaude(nome, descontoConsulta, descontoInternacao);

    }

}


