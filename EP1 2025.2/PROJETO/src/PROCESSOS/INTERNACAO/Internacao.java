package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.Paciente;
import PROCESSOS.CONSULTAS.Status;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Internacao{
    private static int idIUniversal = 1;
    public final int idInternacao;
    public Paciente paciente;
    public Leito leito;
    public LocalDateTime horarioInternacao;
    public LocalDateTime horarioAlta;
    public RelatorioInternacao relatorioInternacao;
    public Status status;

    public Internacao(Paciente paciente, Leito leito) {
        this.idInternacao = idIUniversal++;
        this.paciente = paciente;
        this.leito = leito;
        try {
            this.leito.ocupar(paciente);
        } catch (LeitoOcupado e) {
            throw e;
        }
        this.horarioInternacao = LocalDateTime.now();
        this.horarioAlta = null;
        this.relatorioInternacao = null;
        this.status = Status.EM_PROCESSO;
    }

    public void registrarAlta() {
        if (this.horarioAlta == null) {
            this.horarioAlta = LocalDateTime.now();
            this.leito.liberar();
            this.status = Status.FINALIZADO;
            System.out.println("Alta registrada para " + this.paciente.nome +
                    ". Leito " + this.leito.getIdLeito() + " liberado.");

            // this.relatorioInternacao = new RelatorioInternacao(...);
        }
    }

    public int getIdInternacao() {
        return idInternacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Leito getLeito() {
        return leito;
    }

    public LocalDateTime getHorarioInternacao() {
        return horarioInternacao;
    }

    public LocalDateTime getHorarioAlta() {
        return horarioAlta;
    }

    public boolean isAtiva() {
        return horarioAlta == null;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        String dataEntrada = horarioInternacao.format(formatter);
        String dataSaida = (horarioAlta == null) ? "Ainda internado" : horarioAlta.format(formatter);

        return String.format(
                "--- Internação #%d ---%n" +
                        "Paciente: %s%n" +
                        "Leito: %d (%s)%n" +
                        "Data de Entrada: %s%n" +
                        "Data de Alta: %s",
                idInternacao,
                paciente.nome,
                leito.getIdLeito(),
                leito.getTipoLeito(),
                dataEntrada,
                dataSaida
        );
    }
}

