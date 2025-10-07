package PROCESSOS.CONSULTAS;
import ENTIDADES.MEDICO.Medico;
import java.time.LocalDateTime;

public class HorarioIndisponivel extends Exception {
    public HorarioIndisponivel(Medico medico, LocalDateTime horarioConsulta) {
        super("O horário "+ horarioConsulta + "\n" +
                "está indisponível para o(a) Dr.(a) " + medico.getNome());
    }

}
