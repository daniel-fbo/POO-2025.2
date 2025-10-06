package REPOSITORIOS.RMEDICO;
import ENTIDADES.MEDICO.Medico;
import java.util.List;

public interface REP_MEDICO {
    void salvar(Medico medico);
    Medico buscarCrm(String crm);
    List<Medico> listarTodos();
}
