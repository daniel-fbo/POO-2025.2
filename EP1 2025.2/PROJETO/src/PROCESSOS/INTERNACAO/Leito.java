package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.*;

public class Leito {
    private final short idLeito;
    private boolean ocupado;
    private final TipoLeito tipoLeito;
    private Paciente paciente;
    private double custoDiario;

    public Leito(short codigoLeito, TipoLeito tipoLeito, double custoDiario) {
        this.idLeito = codigoLeito;
        this.tipoLeito = tipoLeito;
        this.ocupado = false;
        this.paciente = null;
        this.custoDiario = custoDiario;
    }

    public short getIdLeito(){
        return idLeito;
    }
    public TipoLeito getTipoLeito() {
        return tipoLeito;
    }
    public boolean isOcupado(){
        return ocupado;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void ocupar(Paciente paciente) {
        if (this.ocupado) {
            throw new LeitoOcupado("Leito " + idLeito + " já está ocupado!");
        }
        this.paciente = paciente;
        this.ocupado = true;
    }
    public void liberar() {
        this.paciente = null;
        this.ocupado = false;
    }

    @Override
    public String toString() {
        return "Leito: " + idLeito + ". \n" +
                "Tipo de leito: "+ tipoLeito + ". \n" +
                "Ocupado por " + (paciente == null ?  "—" : paciente.nome);
    }
}




