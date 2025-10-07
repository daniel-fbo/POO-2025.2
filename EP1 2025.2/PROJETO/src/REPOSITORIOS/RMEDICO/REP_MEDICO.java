package REPOSITORIOS.RMEDICO;
import ENTIDADES.MEDICO.Medico;
import java.util.*;

public interface REP_MEDICO {
    void salvarMedico(Medico medico);
    Optional<Medico> buscarCrm(String crm);
    List<Medico> listarMedicos();
}
