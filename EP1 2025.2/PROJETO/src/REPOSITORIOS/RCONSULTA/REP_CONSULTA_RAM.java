package REPOSITORIOS.RCONSULTA;
import PROCESSOS.CONSULTAS.Consulta;
import java.util.*;
public class REP_CONSULTA_RAM implements REP_CONSULTA {
    List<Consulta> listaConsultas = new ArrayList<>();

    @Override
    public void salvar(Consulta consulta) {
        listaConsultas.removeIf(c -> c.idConsulta == consulta.idConsulta);
        listaConsultas.add(consulta);
    }

    @Override
    public Optional<Consulta> buscarIdConsulta(int idConsulta) {
        return listaConsultas.stream()
                .filter(c->c.idConsulta == idConsulta)
                .findFirst();
    }

    @Override
    public List<Consulta> listarConsultas() {
        return Collections.unmodifiableList(listaConsultas);
    }
}
