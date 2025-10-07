package REPOSITORIOS.RCONSULTA;
import PROCESSOS.CONSULTAS.Consulta;
import java.util.*;

public interface REP_CONSULTA {
    void salvar(Consulta consulta);
    Optional<Consulta> buscarIdConsulta(int idConsulta);
    List<Consulta> listarConsultas();


}
