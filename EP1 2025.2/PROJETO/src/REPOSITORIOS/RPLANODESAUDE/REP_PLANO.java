package REPOSITORIOS.RPLANODESAUDE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;
import java.util.*;

public interface REP_PLANO {
    void imprimirPlanos();
    void salvarPlano(PlanoDeSaude plano);
    Optional<PlanoDeSaude> buscarIdPlano(int idPlano);
    List<PlanoDeSaude> listarPlanos();
}
