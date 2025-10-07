package ENTIDADES.MEDICO;
import PROCESSOS.CONSULTAS.Consulta;
import PROCESSOS.CONSULTAS.HorarioIndisponivel;
import java.time.LocalDateTime;
import java.util.*;

public class Agenda {

/////////////////////   ATRIBUTOS    /////////////////////

    private final List<Consulta> listaConsultas;

//////////////////////  CONSTRUTOR   /////////////////////

    public Agenda() {
        this.listaConsultas = new ArrayList<>();
    }

 /////////////////  GETTERS & SETTERS   //////////////////

    public List<Consulta> getConsultas() {
        return Collections.unmodifiableList(listaConsultas);
    }

/////////////////////  MÉTODOS   ///////////////////////

    public boolean isDisponivel(LocalDateTime horarioDesejado) {
        return listaConsultas.stream()
                .noneMatch(consulta -> consulta.getData().equals(horarioDesejado));
    }

    public void adicionarConsulta(Consulta novaConsulta) throws HorarioIndisponivel {
    LocalDateTime horarioDesejado = novaConsulta.getData();
    if (!isDisponivel(horarioDesejado)) {
        throw new HorarioIndisponivel(novaConsulta.getMedico(), horarioDesejado);
    }
    this.listaConsultas.add(novaConsulta);
}

}