package PROCESSOS.CONSULTAS;
import ENTIDADES.PACIENTE.*;
import ENTIDADES.MEDICO.*;
import SISTEMAS.Status;
import java.time.LocalDateTime;


public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime horarioConsulta;
    private RelatorioConsulta relatorioConsulta;
    private Status status;
    private Especialidades especialidade;
    private double custoFinal;


    public Consulta(Paciente paciente, Medico medico, Especialidades especialidade, LocalDateTime horarioConsulta, double custoFinal) {
        this.paciente = paciente;
        this.medico = medico;
        this.especialidade = especialidade;
        this.horarioConsulta = horarioConsulta;
        this.custoFinal = custoFinal;
        this.status = Status.MARCADO;
    }

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

    @Override
    public String toString() {
        return "Consulta com Dr (a). " + medico.getNome()  + "\n" +
                " — Paciente: " + paciente.getNome() + "\n" +
                " — Data e horário: " + horarioConsulta + "\n" +
                " — Valor final: R$" + String.format("%.2f", custoFinal);
    }

}


