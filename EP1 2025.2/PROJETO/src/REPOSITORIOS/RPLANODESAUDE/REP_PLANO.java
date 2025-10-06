package REPOSITORIOS.RPLANODESAUDE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;
import java.util.List;
import java.util.Optional;

public interface REP_PLANO {
    void salvar(PlanoDeSaude plano);
    Optional<PlanoDeSaude> buscarNome(String nome);
    List<PlanoDeSaude> listarTodos();
}
