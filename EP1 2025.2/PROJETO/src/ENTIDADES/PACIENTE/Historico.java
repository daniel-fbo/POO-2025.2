package ENTIDADES.PACIENTE;
import PROCESSOS.CONSULTAS.RelatorioConsulta;
import PROCESSOS.INTERNACAO.RelatorioInternacao;
import java.util.*;


public class Historico {
    public List<RelatorioConsulta> historicoConsultas = new ArrayList<>();
    public List<RelatorioInternacao> historicoInternacao = new ArrayList<>();

    public void adicionarConsultaHistorico(RelatorioConsulta relatorioConsulta){
        historicoConsultas.add(relatorioConsulta);
    }

    public void adicionarInternacaoHistorico(RelatorioInternacao relatorioInternacao){
        historicoInternacao.add(relatorioInternacao);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Histórico do Paciente ---\n");

        sb.append("\nConsultas:\n");
        if (historicoConsultas.isEmpty()) {
            sb.append("Nenhuma consulta registrada.\n");
        } else {
            for (RelatorioConsulta consulta : historicoConsultas) {
                sb.append(consulta.toString()).append("\n\n");
            }
        }

        sb.append("Internações:\n");
        if (historicoInternacao.isEmpty()) {
            sb.append("Nenhuma internação registrada.\n");
        } else {
            for (RelatorioInternacao internacao : historicoInternacao) {
                sb.append(internacao.toString()).append("\n\n");
            }
        }

        return sb.toString();
    }

}
