package ENTIDADES.MEDICO;

public class Medico {
    public final String nome;
    public final String crm;
    public double custoConsulta;
    public Agenda agenda;
    public Especialidades especialidade;

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
