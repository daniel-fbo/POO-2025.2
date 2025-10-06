package REPOSITORIOS.RINTERNACAO;
import PROCESSOS.INTERNACAO.Internacao;
import java.util.List;

public interface REP_INTERNACAO {
    void salvar(Internacao internacao);
    Internacao buscarIdI(int idInternacao);
    List<Internacao> listarTodos();
}
