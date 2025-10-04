package PROCESSOS.CONSULTAS;

public class HorarioIndisponivel extends RuntimeException {
    public HorarioIndisponivel(String message) {
        super(message);
    }
}
