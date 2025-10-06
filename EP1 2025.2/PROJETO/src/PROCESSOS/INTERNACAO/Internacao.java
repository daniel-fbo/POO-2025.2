package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.Paciente;
import java.time.LocalDateTime;

public class Internacao{
    private static int idIUniversal;
    private final int idInternacao;
    private Paciente paciente;
    private Leito leito;
    private LocalDateTime horarioInternacao;
    private LocalDateTime horarioAlta;
    private RelatorioInternacao relatorioInternacao;

}
