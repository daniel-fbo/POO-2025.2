package ENTIDADES.PLANODESAUDE;
import ENTIDADES.MEDICO.Especialidades;

public class PlanoDeSaude {

/////////////////////   ATRIBUTOS    /////////////////////

    public static int idPUniversal = 1 ;
    public int idPlano;
    public String nome;
    public double descontoConsulta;
    public double descontoInternacao;
    private Especialidades especialidade;

//////////////////////  CONSTRUTOR   /////////////////////

    public PlanoDeSaude(String nome, double descontoConsulta, double descontoInternacao, Especialidades especialidade){
        this.idPlano = idPUniversal;
        this.nome = nome;
        this.descontoConsulta = descontoConsulta;
        this.descontoInternacao = descontoInternacao;
        this.especialidade = especialidade;
    }

    //CSV
    public PlanoDeSaude(int id, String nome, double descontoConsulta, double descontoInternacao, Especialidades especialidade){
        this.idPlano = id;
        this.nome = nome;
        this.descontoConsulta = descontoConsulta;
        this.descontoInternacao = descontoInternacao;
        this.especialidade = especialidade;
    }
    public PlanoDeSaude(String nome) {
        this.nome = nome;
    }

    /////////////////  GETTERS & SETTERS   //////////////////

    public Especialidades getEspecialidade(){
        return especialidade;
    }
    public String getNome(){
        return nome;
    }
    public int getIdPlano() {return idPlano;
    }
    public double getDescontoConsulta() {return descontoConsulta;
    }

    public double getDescontoInternacao() {return descontoInternacao;
    }
}
