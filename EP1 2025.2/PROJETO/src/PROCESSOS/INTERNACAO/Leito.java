package PROCESSOS.INTERNACAO;
import ENTIDADES.PACIENTE.*;
public class Leito {

/////////////////////   ATRIBUTOS    /////////////////////

    private final short idLeito;
    private boolean ocupado;
    private final TipoLeito tipoLeito;
    private Paciente paciente;
    private double custoDiario;

//////////////////////  CONSTRUTOR   /////////////////////

    public Leito(short codigoLeito, TipoLeito tipoLeito, double custoDiario) {
        this.idLeito = codigoLeito;
        this.tipoLeito = tipoLeito;
        this.ocupado = false;
        this.paciente = null;
        this.custoDiario = custoDiario;
    }
    public Leito(short codigoLeito, TipoLeito tipoLeito) {
        this.idLeito = codigoLeito;
        this.tipoLeito = tipoLeito;
    }
/////////////////  GETTERS & SETTERS   //////////////////

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

    public double getCustoDiario() {
        return custoDiario;
    }

/////////////////////  MÉTODOS   ///////////////////////

    public void ocupar(Paciente paciente) throws LeitoOcupado {
        if (this.ocupado) {
            throw new LeitoOcupado(this, this.paciente);
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




