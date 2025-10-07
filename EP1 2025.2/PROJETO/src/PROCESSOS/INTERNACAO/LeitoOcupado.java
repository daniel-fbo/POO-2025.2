package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.Paciente;

public class LeitoOcupado extends Exception {
    public LeitoOcupado(Leito leito, Paciente paciente) {
        super("O leito "+ leito.getIdLeito() + "está ocupado! " + "\n" +
                "pelo paciente " + paciente.getNome());
    }

}


