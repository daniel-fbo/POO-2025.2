package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.*;

public class Leito {
    private final short codigoLeito;
    private boolean ocupado;
    private final TipoLeito tipoLeito;
    private Paciente paciente;
    private double custoDiario;

    public Leito(short codigoLeito, TipoLeito tipoLeito, double custoDiario) {
        this.codigoLeito = codigoLeito;
        this.tipoLeito = tipoLeito;
        this.ocupado = false;
        this.paciente = null;
        this.custoDiario = custoDiario;
    }

}