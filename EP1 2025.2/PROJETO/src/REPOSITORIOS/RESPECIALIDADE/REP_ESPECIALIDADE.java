package REPOSITORIOS.RESPECIALIDADE;
import ENTIDADES.MEDICO.Especialidades;
import java.util.List;

public interface REP_ESPECIALIDADE {
    void salvarEspecialidade(Especialidades especialidade);
    List<Especialidades> listarEspecialidades();
}