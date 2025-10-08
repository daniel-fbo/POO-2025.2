/*package REPOSITORIOS.RPLANODESAUDE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;
import java.util.*;

public class REP_PLANO_RAM implements REP_PLANO {
    private static final List<PlanoDeSaude> listaPlanos = new ArrayList<>();

    @Override
    public void salvarPlano(PlanoDeSaude plano){
        listaPlanos.removeIf(p -> p.idPlano == plano.idPlano);
        listaPlanos.add(plano);
    }

    @Override
    public Optional<PlanoDeSaude> buscarIdPlano(int idPlano){
        return listaPlanos.stream()
                .filter(p -> p.idPlano == idPlano )
                .findFirst();
    }

    @Override
    public List<PlanoDeSaude> listarPlanos(){
        return Collections.unmodifiableList(listaPlanos);
    }

}*/
