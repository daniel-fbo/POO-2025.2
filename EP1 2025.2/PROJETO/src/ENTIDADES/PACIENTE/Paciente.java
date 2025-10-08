package ENTIDADES.PACIENTE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;

public class Paciente {

/////////////////////   ATRIBUTOS    /////////////////////

    public final String nome;
    public  String cpf;
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
        this.historico = new Historico();
    }

    //CSV
    public Paciente(String nome, String cpf, Historico historico) {
        this.nome = nome;
        this.cpf = cpf;
        this.historico = historico;
    }
    public Paciente(String nome) {
        this.nome = nome;
    }
    public Paciente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
/////////////////  GETTERS & SETTERS   //////////////////
    public PlanoDeSaude getPlano() {return plano;}
    public String getNome(){
        return nome;
    }
    public Historico getHistorico() {
        if (historico == null) historico = new Historico(); // segurança extra
        return historico;
    }

    public String getCpf(){
        return cpf;
    }
}
