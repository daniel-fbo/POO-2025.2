package REPOSITORIOS.RINTERNACAO;
import PROCESSOS.INTERNACAO.Internacao;
import java.util.List;
import java.util.Optional;

public interface REP_INTERNACAO {
    void salvar(Internacao internacao);
    Optional<Internacao> buscarCpfAtivo(String cpf);
    Optional<Internacao> buscarCpf(String cpf);
    List<Internacao> listarInternacoes();
}
