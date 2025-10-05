package PROCESSOS.INTERNACAO;

public class LeitoOcupado extends RuntimeException {

    public LeitoOcupado(String message) {
        super(message);
    }

    public LeitoOcupado() {
        super("O leito já está ocupado!");
    }
}
