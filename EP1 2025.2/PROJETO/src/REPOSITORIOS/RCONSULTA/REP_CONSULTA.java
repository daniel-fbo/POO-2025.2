package REPOSITORIOS.RCONSULTA;
import PROCESSOS.CONSULTAS.Consulta;
import java.util.List;

public interface REP_CONSULTA {
    void salvar(Consulta consulta);
    Consulta buscarIdC(int idConsulta);
    List<Consulta> listarTodos();
}
