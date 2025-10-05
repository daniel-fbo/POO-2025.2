package ENTIDADES.MEDICO;
public class Medico {
    private final String nome;
    private final short crm;
    private double custoConsulta;
    private Agenda agenda;
    private Especialidades especialidade;

    public Medico(String nome, short crm, double custoConsulta, Especialidades especialidade){
        this.nome = nome;
        this.crm = crm;
        this.custoConsulta = custoConsulta;
        this.especialidade = especialidade;
    }

}
