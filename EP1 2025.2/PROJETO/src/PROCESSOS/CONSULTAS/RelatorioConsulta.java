package PROCESSOS.CONSULTAS;
import ENTIDADES.MEDICO.Especialidades;
import ENTIDADES.MEDICO.Medico;
import ENTIDADES.PACIENTE.Paciente;
import java.time.LocalDateTime;

public record RelatorioConsulta(
    int idConsulta,
    Paciente paciente,
    Medico medico,
    LocalDateTime horarioConsulta,
    Status status,
    Especialidades especialidade,
    double custoFinal,
    Diagnostico diagnostico)  {

    @Override
    public String toString() {
        String string = String.format(
                "--- Relatório de Consulta ---%n" +
                        "Código da Consulta: %d%n" +
                        "Paciente: %s%n" +
                        "CPF do Paciente: %s%n" +
                        "Dr(a): %s%n" +
                        "Campo da consulta: %s%n" +
                        diagnostico.toString() +
                        "CUSTO TOTAL A PAGAR: R$ %.2f",
                idConsulta,
                paciente.nome,
                paciente.cpf,
                medico.nome,
                especialidade,
                diagnostico,
                custoFinal);
        return string;
    }

}
