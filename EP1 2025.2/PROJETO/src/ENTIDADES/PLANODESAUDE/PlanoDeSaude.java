package ENTIDADES.PLANODESAUDE;
import ENTIDADES.MEDICO.Especialidades;

public class PlanoDeSaude {
    private String nome;
    private float descontoConsulta;
    private float descontoInternacao;
    private Especialidades especialidade;

    public PlanoDeSaude(String nome, float descontoConsulta, float descontoInternacao, Especialidades especialidade){
        this.nome = nome;
        this.descontoConsulta = descontoConsulta;
        this.descontoInternacao = descontoInternacao;
        this.especialidade = especialidade;
    }
}
