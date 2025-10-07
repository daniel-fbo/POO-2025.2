package SISTEMAS;
import ENTIDADES.PACIENTE.Paciente;
import PROCESSOS.INTERNACAO.*;
import REPOSITORIOS.RPACIENTE.*;
import REPOSITORIOS.RINTERNACAO.*;
import java.util.Optional;
import java.util.Scanner;
import ENTIDADES.PACIENTE.EstadoPaciente;

public class SistemaInternacao implements Menu {
    private final Scanner input;
    private final REP_PACIENTE rPaciente;
    private final REP_INTERNACAO rInternacao;
    private final GerenciadorLeitos gerenciadorLeitos;
    public SistemaInternacao(Scanner input, REP_PACIENTE rPaciente, REP_INTERNACAO rInternacao, GerenciadorLeitos gLeitos) {
        this.input = input;
        this.rPaciente = rPaciente;
        this.rInternacao = rInternacao;
        this.gerenciadorLeitos = gLeitos;
    }


    @Override
    public void abrirMenu(){
        String tecla;
        do {
            System.out.println("\n==== SISTEMA DE INTERNAÇÃO ====");
            System.out.println("1 - Internar paciente.");
            System.out.println("2- Liberar paciente.");
            System.out.println("3 - Verificar situação dos leitos.");
            System.out.print("Digite a opção desejada: ");
            tecla = input.nextLine();
            try{
                switch (tecla) {
                    case "1" -> internarPaciente();
                    case "2" -> liberarPaciente();
                    case "3" -> verificarLeitos();
                    case "4" -> System.out.println("SISTEMA FECHADO");
                    default -> System.out.println("Opção inválida.");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Por favor, tente novamente.");
            }
        } while (!tecla.equals("4"));
    }

    public void internarPaciente() throws LeitoOcupado{
        System.out.println("\n--- Nova Internação ---");
        System.out.print("Digite o CPF do paciente a ser internado: ");
        String cpf = input.nextLine();

        Optional<Paciente> oPaciente = rPaciente.buscarCpf(cpf);
        if (oPaciente.isEmpty()) {
            System.out.println("Paciente não encontrado.");
            return;
        }
        Paciente paciente = oPaciente.get();
        System.out.println("Paciente encontrado: " + paciente.nome);

        EstadoPaciente estado = paciente.estado;
        TipoLeito tipoLeito = switch (estado) {
            case Azul, Verde -> TipoLeito.QUARTO;
            case Amarelo -> TipoLeito.SALA_AMARELA;
            case Vermelho -> TipoLeito.SALA_VERMELHA;
        };

        System.out.println("Buscando " + tipoLeito + "...");
        Optional<Leito> oLeitoDisponivel = gerenciadorLeitos.getLeitoDisponivel(tipoLeito);

        if (oLeitoDisponivel.isEmpty()) {
            System.out.println("Nenhum " + tipoLeito + " está disponível no momento.");
            return;
        }

        Leito leito = oLeitoDisponivel.get();
        System.out.println("Leito ("+leito.getIdLeito()+") encontrado.");

        try {
            Internacao novaInternacao = new Internacao(paciente, leito);
            rInternacao.salvar(novaInternacao);
            System.out.println(novaInternacao);

        } catch (LeitoOcupado e) {
            System.out.println(e.getMessage());
        }
    };

    public void liberarPaciente(){
        System.out.println("\n--- Registrar Alta de Paciente ---");
        System.out.print("Digite o CPF do paciente que receberá alta: ");
        String cpf = input.nextLine();

        Optional<Internacao> oInternacao = rInternacao.buscarCpfAtivo(cpf);
        if (oInternacao.isEmpty()){
            System.out.println("Nenhuma internação ativa encontrada para o CPF: " + cpf);
            return;
        }
        Internacao internacao = oInternacao.get();
        RelatorioInternacao relatorio = internacao.registrarAlta();
        rInternacao.salvar(internacao);
        System.out.println("\n+++ ALTA REALIZADA COM SUCESSO +++");
        System.out.println(relatorio);
    };

    public void verificarLeitos(){
        System.out.println(gerenciadorLeitos);
    };
}
