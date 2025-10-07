package ENTIDADES.MEDICO;

public class Medico {

/////////////////////   ATRIBUTOS    /////////////////////

    public final String nome;
    public final String crm;
    public double custoConsulta;
    public Agenda agenda;
    public Especialidades especialidade;

//////////////////////  CONSTRUTOR   /////////////////////

    public Medico(String nome, String crm, double custoConsulta, Especialidades especialidade){
        this.nome = nome;
        this.crm = crm;
        this.custoConsulta = custoConsulta;
        this.especialidade = especialidade;
        this.agenda = null;
    }

/////////////////  GETTERS & SETTERS   //////////////////

    public String getNome(){
        return nome;
    }

    public Agenda getAgenda(){
        return agenda;
    }

    public String getCrm(){
        return crm;
    }

    public Especialidades getEspecialidade(){
        return especialidade;
    }

}
