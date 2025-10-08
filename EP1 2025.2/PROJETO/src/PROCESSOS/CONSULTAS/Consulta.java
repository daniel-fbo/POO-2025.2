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

//////////////////////  CONSTRUTORES   /////////////////////

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
    // CSV
    public Consulta(int id, Paciente paciente, Medico medico, LocalDateTime horario, double custo, Status status) {
        this.idConsulta = id; // Usa o ID lido do arquivo
        this.paciente = paciente;
        this.medico = medico;
        this.especialidade = medico.getEspecialidade();
        this.horarioConsulta = horario;
        this.custoFinal = custo;
        this.status = status; // Usa o Status lido do arquivo
    }

 /////////////////  GETTERS & SETTERS   //////////////////

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public int getIdConsulta() {return idConsulta;
    }
    public LocalDateTime getHorarioConsulta() {return horarioConsulta;
    }

    public LocalDateTime getData() {
        return horarioConsulta;
    }

    public double getCustoFinal() {
        return custoFinal;
    }

    public void setDiagnostico(Diagnostico diagnostico){
        this.diagnostico = diagnostico;
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


    public Status getStatus() { return status; }
}


