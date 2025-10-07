package PROCESSOS.CONSULTAS;
import ENTIDADES.PACIENTE.*;
import ENTIDADES.MEDICO.*;
import java.time.LocalDateTime;

public class Consulta {

/////////////////////   ATRIBUTOS    /////////////////////

    public static int idCUniversal = 1 ;
    public final int idConsulta;
    public final Paciente paciente;
    public final Medico medico;
    public final LocalDateTime horarioConsulta;
    public RelatorioConsulta relatorioConsulta;
    public Status status;
    public Especialidades especialidade;
    public double custoFinal;
    public Diagnostico diagnostico;

//////////////////////  CONSTRUTOR   /////////////////////

    public Consulta(Paciente paciente, Medico medico, LocalDateTime horarioConsulta, double custoFinal) {
        this.idConsulta = idCUniversal++;
        this.paciente = paciente;
        this.medico = medico;
        this.especialidade = medico.getEspecialidade();
        this.horarioConsulta = horarioConsulta;
        this.custoFinal = custoFinal;
        this.status = Status.MARCADO;
        this.diagnostico = null;
    }

 /////////////////  GETTERS & SETTERS   //////////////////

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getData() {
        return horarioConsulta;
    }

    public double getCustoFinal() {
        return custoFinal;
    }

    public void setCustoFinal(double custoFinal) {
        this.custoFinal = custoFinal;
    }

/////////////////////  MÉTODOS   ///////////////////////

    public RelatorioConsulta registrarConsulta() {
        return this.relatorioConsulta;
    }

    @Override
    public String toString() {
        return "Consulta com Dr (a). " + medico.getNome()  + "\n" +
                " — Paciente: " + paciente.getNome() + "\n" +
                " — Data e horário: " + horarioConsulta + "\n" +
                " — Valor final: R$" + String.format("%.2f", custoFinal);
    }

}


