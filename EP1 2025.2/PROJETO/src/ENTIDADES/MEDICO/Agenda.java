package ENTIDADES.MEDICO;

import PROCESSOS.CONSULTAS.Consulta;
import PROCESSOS.CONSULTAS.HorarioIndisponivel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {

    private final List<Consulta> listaConsultas;

    public Agenda() {
        this.listaConsultas = new ArrayList<>();
    }

    public boolean isDisponivel(LocalDateTime horarioDesejado) {
        return listaConsultas.stream()
                .noneMatch(c -> c.getData().equals(horarioDesejado));
    }

    public void adicionarConsulta(Consulta novaConsulta) throws HorarioIndisponivel {
        LocalDateTime horarioDesejado = novaConsulta.getData();
        if (!isDisponivel(horarioDesejado)) {
            throw new HorarioIndisponivel(novaConsulta.getMedico(), horarioDesejado);
        }
        this.listaConsultas.add(novaConsulta);
    }

    public List<Consulta> getConsultas() {
        return Collections.unmodifiableList(listaConsultas);
    }
}