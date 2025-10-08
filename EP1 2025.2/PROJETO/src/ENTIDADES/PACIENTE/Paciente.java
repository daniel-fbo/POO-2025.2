package ENTIDADES.PACIENTE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;

public class Paciente {

/////////////////////   ATRIBUTOS    /////////////////////

    public final String nome;
    public final String cpf;
    public short idade;
    public EstadoPaciente estado;
    public Historico historico;
    public PlanoDeSaude plano;

//////////////////////  CONSTRUTOR   /////////////////////

    public Paciente(String nome, String cpf, short idade, EstadoPaciente estado, PlanoDeSaude plano){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.estado = estado;
        this.plano = plano;
        this.historico = null;
    }

/////////////////  GETTERS & SETTERS   //////////////////
    public PlanoDeSaude getPlano() {return plano;}
    public String getNome(){
        return nome;
    }
    public Historico getHistorico(){return historico;}

}
