package REPOSITORIOS.RPLANODESAUDE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;
import java.util.List;

public interface REP_PLANO {
    void salvar(PlanoDeSaude plano);
    PlanoDeSaude buscarNome(String nome);
    List<PlanoDeSaude> listarTodos();
}
