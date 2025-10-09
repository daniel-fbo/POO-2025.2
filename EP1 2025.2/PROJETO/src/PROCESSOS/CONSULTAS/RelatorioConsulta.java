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


    public String toCSV() {
        return idConsulta + ";" +
                paciente.getCpf() + ";" +
                medico.getCrm() + ";" +
                especialidade.getNome() + ";" +
                horarioConsulta + ";" +
                status.name() + ";" +
                custoFinal + ";" +
                (diagnostico != null
                        ? diagnostico.descricao() + "|" + diagnostico.medicacoesSugeridas() + "|" + diagnostico.examesRecomendados()
                        : "");
    }


}
