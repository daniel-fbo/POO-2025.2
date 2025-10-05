package PROCESSOS.INTERNACAO;

import ENTIDADES.PACIENTE.Paciente;
import PROCESSOS.CONSULTAS.RelatorioConsulta;
import java.time.LocalDateTime;

public class Internacao{
    private Paciente paciente;
    private Leito leito;
    private LocalDateTime horarioInternacao;
    private LocalDateTime horarioAlta;
    private RelatorioInternacao relatorioInternacao;

}
