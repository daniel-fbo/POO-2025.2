package REPOSITORIOS.RCONSULTA;
import PROCESSOS.CONSULTAS.Consulta;
import java.util.*;

public interface REP_CONSULTA {
    Optional<Consulta> buscarIdConsulta(int idConsulta);
    List<Consulta> listarConsultas();

    void salvarConsulta(Consulta consulta);

    void atualizarConsulta(Consulta consulta);
}
