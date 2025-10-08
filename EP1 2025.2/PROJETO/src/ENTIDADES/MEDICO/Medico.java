package ENTIDADES.MEDICO;

import ENTIDADES.PACIENTE.Historico;

public class Medico {

/////////////////////   ATRIBUTOS    /////////////////////

    public final String nome;
    public String crm;
    public double custoConsulta;
    public Agenda agenda;
    public Especialidades especialidade;

//////////////////////  CONSTRUTOR   /////////////////////

    public Medico(String nome, String crm, double custoConsulta, Especialidades especialidade){
        this.nome = nome;
        this.crm = crm;
        this.custoConsulta = custoConsulta;
        this.especialidade = especialidade;
        this.agenda = new Agenda();
    }
    public Medico(String nome){
        this.nome = nome;
    }

/////////////////  GETTERS & SETTERS   //////////////////

    public String getNome(){
        return nome;
    }

    public Agenda getAgenda() {
        if (agenda == null) agenda = new Agenda();
        return agenda;
    }

    public double getCustoConsulta(){return custoConsulta;}

    public String getCrm(){
        return crm;
    }

    public Especialidades getEspecialidade(){
        return especialidade;
    }

}
