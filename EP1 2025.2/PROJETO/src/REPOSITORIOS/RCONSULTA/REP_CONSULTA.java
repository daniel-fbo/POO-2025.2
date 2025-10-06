package REPOSITORIOS.RCONSULTA;
import PROCESSOS.CONSULTAS.Consulta;
import java.util.List;
import java.util.Optional;

public interface REP_CONSULTA {
    void salvar(Consulta consulta);
    Optional<Consulta> buscarIdC(int idConsulta);
    List<Consulta> listarTodos();
}
