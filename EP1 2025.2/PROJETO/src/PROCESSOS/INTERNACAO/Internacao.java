package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.Paciente;
import ENTIDADES.PACIENTE.Paciente_Crianca;
import ENTIDADES.PACIENTE.Paciente_Idoso;
import PROCESSOS.CONSULTAS.Status;
import java.time.Duration;
import java.time.LocalDateTime;

public class Internacao{

/////////////////////   ATRIBUTOS    /////////////////////

    private static int idIUniversal = 1;
    public final int idInternacao;
    public Paciente paciente;
    public Leito leito;
    public LocalDateTime horarioInternacao;
    public LocalDateTime horarioAlta;
    public RelatorioInternacao relatorioInternacao;
    public Status status;
    public double iCustoFinal;

//////////////////////  CONSTRUTOR   /////////////////////
    public Internacao(Paciente paciente, Leito leito) throws LeitoOcupado {
        this.idInternacao = idIUniversal++;
        this.paciente = paciente;
        this.leito = leito;
        this.leito.ocupar(paciente);
        this.horarioInternacao = LocalDateTime.now();
        this.horarioAlta = null;
        this.relatorioInternacao = null;
        this.status = Status.EM_PROCESSO;
        this.iCustoFinal = 0;
    }

    public Internacao(int id, String nomePaciente, String cpf,
                      LocalDateTime data, Status status) {
        this.idInternacao = id;
        this.paciente = paciente;
        this.horarioInternacao = data;
        this.status = status;
    }


    /////////////////  GETTERS & SETTERS   //////////////////

    public Paciente getPaciente() {
        return paciente;
    }

    public int getIdInternacao() {return idInternacao;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDataInternacao() {
        return horarioInternacao;
    }

/////////////////////  MÉTODOS   ///////////////////////

    private double calcularCusto(Duration duracao) {
    double custoDiario = leito.getCustoDiario();
    long dias = duracao.toDays();
    if (duracao.toHours() % 24 > 0) {
        dias += 1;
    }
    double custoFinal = custoDiario * dias;

    if(paciente.plano!=null){
        custoFinal *= paciente.plano.descontoInternacao;
    }

    if (paciente instanceof Paciente_Idoso) {
        custoFinal *= 1.15;
    } else if (paciente instanceof Paciente_Crianca) {
        custoFinal *= 0.9;
    }


    /*if (paciente.getPlano().equals(Plano Starplatinum) && dias > 10) {
        custoFinal = 0;
    }*/

    return custoFinal;
    }

    public boolean isAtiva() {
        return horarioAlta == null;
    }

    public RelatorioInternacao registrarAlta() {
        if (this.horarioAlta == null) {
            this.horarioAlta = LocalDateTime.now();
            this.leito.liberar();

            Duration duracao = Duration.between(this.horarioInternacao, this.horarioAlta);
            double custo = calcularCusto(duracao);
            this.relatorioInternacao = new RelatorioInternacao(this.idInternacao, this.paciente, this.leito, duracao, custo);
            return this.relatorioInternacao;
        }
        return this.relatorioInternacao;
    }


}

