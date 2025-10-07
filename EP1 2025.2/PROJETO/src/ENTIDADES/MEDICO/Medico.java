package ENTIDADES.MEDICO;

import ENTIDADES.ESPECIALIDADE.Especialidades;

public class Medico {
    private final String nome;
    private final String crm;
    private double custoConsulta;
    private Agenda agenda;
    private Especialidades especialidade;

    public Medico(String nome, String crm, double custoConsulta, Especialidades especialidade){
        this.nome = nome;
        this.crm = crm;
        this.custoConsulta = custoConsulta;
        this.especialidade = especialidade;
        this.agenda = null;
    }

    public String getNome(){
        return nome;
    }
    public String getCrm(){
        return crm;
    }
    public Especialidades getEspecialidade(){
        return especialidade;
    }

}
