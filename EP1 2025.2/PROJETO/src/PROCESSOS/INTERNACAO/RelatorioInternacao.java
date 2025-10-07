package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.Paciente;
import java.time.Duration;

public record RelatorioInternacao(
        int idInternacao,
        Paciente paciente,
        Leito leito,
        Duration duracao,
        double custoFinal) {

    @Override
    public String toString() {
        long dias = duracao.toDays();
        long horas = duracao.toHoursPart();

        String string = String.format(
                "--- Relatório de Alta Hospitalar ---%n" +
                        "Código da Internação: %d%n" +
                        "Paciente: %s%n" +
                        "CPF do Paciente: %s%n" +
                        "Leito Utilizado: %d (%s)%n" +
                        "Tempo de Internação: %d dias e %d horas%n" +
                        "CUSTO TOTAL A PAGAR: R$ %.2f",
                idInternacao,
                paciente.nome,
                paciente.cpf,
                leito.getIdLeito(),
                leito.getTipoLeito(),
                dias,
                horas,
                custoFinal);

        return string;
    }
}