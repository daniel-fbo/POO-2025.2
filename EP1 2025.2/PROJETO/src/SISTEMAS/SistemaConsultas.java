package SISTEMAS;
import PROCESSOS.CONSULTAS.Consulta;
import PROCESSOS.CONSULTAS.RelatorioConsulta;
import REPOSITORIOS.RCONSULTA.REP_CONSULTA;
import java.util.*;

public class SistemaConsultas implements Menu {
    private Scanner input;
    private REP_CONSULTA rConsulta;
    public SistemaConsultas(Scanner input, REP_CONSULTA rConsulta){
        this.input = input;
        this.rConsulta = rConsulta;
    }

    @Override
    public void abrirMenu(){
        String tecla;
        do {
            System.out.println("\n==== SISTEMA DE CONSULTAS ====");
            System.out.println("1 - Agendar consulta.");
            System.out.println("2 - Gerar relatório de consulta.");
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

    };

    public void processarConsulta(){
        System.out.println("\n--- Gerar Relatório de Consoulta ---");
        System.out.print("Digite o código de identificação da consulta: ");
        int idCOnsulta = input.nextInt();

        Optional<Consulta> oConsulta = rConsulta.buscarIdConsulta(idCOnsulta);
        if (oConsulta.isEmpty()){
            System.out.println("Nenhuma internação ativa encontrada para o CPF: " + idCOnsulta);
            return;
        }
        Consulta consulta = oConsulta.get();
        RelatorioConsulta relatorio = consulta.registrarConsulta();
        rConsulta.salvar(consulta);
        System.out.println("\n+++ ALTA REALIZADA COM SUCESSO +++");
        System.out.println(relatorio);

    };

}
