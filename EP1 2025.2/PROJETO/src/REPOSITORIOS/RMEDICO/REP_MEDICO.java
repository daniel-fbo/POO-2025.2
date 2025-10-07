package REPOSITORIOS.RMEDICO;
import ENTIDADES.MEDICO.*;
import java.util.*;

public interface REP_MEDICO {
    void salvarMedico(Medico medico);
    Optional<Medico> buscarCrm(String crm);
    Optional<Medico> buscarEspecialidade(Especialidades especialidade);
    List<Medico> listarMedicos();
}
