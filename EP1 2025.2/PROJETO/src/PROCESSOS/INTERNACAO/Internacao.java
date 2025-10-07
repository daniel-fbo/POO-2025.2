package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.Paciente;
import PROCESSOS.CONSULTAS.Status;

import java.time.Duration;
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

    public Internacao(Paciente paciente, Leito leito) throws LeitoOcupado {
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

    public RelatorioInternacao registrarAlta() {
        if (this.horarioAlta == null) {
            this.horarioAlta = LocalDateTime.now();
            this.leito.liberar();

            Duration duracao = Duration.between(this.horarioInternacao, this.horarioAlta);
            double custo = calcularCustoTotal(duracao);
            this.relatorioInternacao = new RelatorioInternacao(this.idInternacao, this.paciente, this.leito, duracao, custo);
            return this.relatorioInternacao;
        }
        return this.relatorioInternacao;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public boolean isAtiva() {
        return horarioAlta == null;
    }

}

