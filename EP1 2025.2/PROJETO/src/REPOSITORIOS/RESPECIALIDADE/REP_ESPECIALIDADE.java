package REPOSITORIOS.RESPECIALIDADE;
import ENTIDADES.ESPECIALIDADE.Especialidades;
import java.util.List;

public interface REP_ESPECIALIDADE {
    void salvarEspecialidade(Especialidades especialidade);
    List<Especialidades> listarEspecialidades();
}