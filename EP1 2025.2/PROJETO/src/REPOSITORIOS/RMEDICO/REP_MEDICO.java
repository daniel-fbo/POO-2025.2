package REPOSITORIOS.RMEDICO;
import ENTIDADES.MEDICO.Medico;
import java.util.List;
import java.util.Optional;

public interface REP_MEDICO {
    void salvar(Medico medico);
    Optional<Medico> buscarCrm(String crm);
    List<Medico> listarTodos();
}
