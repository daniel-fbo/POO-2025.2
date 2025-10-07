package ENTIDADES.PACIENTE;
import PROCESSOS.CONSULTAS.RelatorioConsulta;
import PROCESSOS.INTERNACAO.RelatorioInternacao;
import java.util.*;

public class Historico {
    public List<RelatorioConsulta> historicoConsultas = new ArrayList<>();
    public List<RelatorioInternacao> historicoInternacao = new ArrayList<>();
}
