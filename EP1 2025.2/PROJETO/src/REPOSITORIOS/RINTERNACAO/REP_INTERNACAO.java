package REPOSITORIOS.RINTERNACAO;
import PROCESSOS.INTERNACAO.Internacao;
import java.util.List;
import java.util.Optional;

public interface REP_INTERNACAO {
    void salvar(Internacao internacao);
    Optional<Internacao> buscarIdI(int idInternacao);
    List<Internacao> listarTodos();
}
