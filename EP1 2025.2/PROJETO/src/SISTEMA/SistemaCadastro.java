package SISTEMA;
import java.util.*;
import java.io.*;
import ENTIDADES.PACIENTE.*;
import ENTIDADES.MEDICO.*;
public class SistemaCadastro implements TEXTO {
    Scanner input = new Scanner(System.in);
    String tecla;

    @Override
    public void abrirMenu() {
        do {
            System.out.println("\n==== SISTEMA DE CADASTRO ====");
            System.out.println("1 - Cadastro de paciente.");
            System.out.println("2 - Cadastro de médico.");
            System.out.println("3 - Voltar ao menu principal: ");
            tecla = input.nextLine();
            switch (tecla) {
                case "1" -> cadastrarPaciente();
                case "2" -> cadastrarMedico();
                case "3" -> System.out.println("SISTEMA FECHADO");
                default -> System.out.println("Opção inválida.");
            }
        } while (!tecla.equals("3"));
    }

    public void cadastrarPaciente () {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("CPF: ");
        String cpf = input.nextLine();
        System.out.print("Idade: ");
        short idade = input.nextShort();
        if (idade <= 12) {
            Paciente_Crianca paciente = new Paciente_Crianca(nome, cpf, idade);
        } else if (idade >= 60){
            Paciente_Idoso paciente = new Paciente_Idoso(nome, cpf, idade);
        } else {
            Paciente paciente = new Paciente(nome, cpf, idade);
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

    public void cadastroPlano(){
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Porcentagem de desconto sobre : ");
        String cpf = input.nextLine();
        System.out.print("Idade: ");
        short idade = input.nextShort();


    }

}


