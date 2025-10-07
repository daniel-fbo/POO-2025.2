package PROCESSOS.CONSULTAS;
import ENTIDADES.PACIENTE.*;
import ENTIDADES.MEDICO.*;
import ENTIDADES.MEDICO.Especialidades;
import SISTEMAS.Status;
import java.time.LocalDateTime;


public class Consulta {
    private static int idCUniversal = 1 ;
    private final int idConsulta;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDateTime horarioConsulta;
    private RelatorioConsulta relatorioConsulta;
    private Status status;
    private Especialidades especialidade;
    private double custoFinal;


    public Consulta(Paciente paciente, Medico medico, LocalDateTime horarioConsulta, double custoFinal) {
        this.idConsulta = idCUniversal++;
        this.paciente = paciente;
        this.medico = medico;
        this.especialidade = medico.getEspecialidade();
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


