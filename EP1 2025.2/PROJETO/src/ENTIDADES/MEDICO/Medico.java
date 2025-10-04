package ENTIDADES.MEDICO;
public class Medico {
    private String nome;
    private short crm;
    private double custoConsulta;
    private Agenda agenda;
    private Especialidades especialidade;

    public Medico(String nome, short crm, double custoConsulta){
        this.nome = nome;
        this.crm = crm;
        this.custoConsulta = custoConsulta;
    }

}
