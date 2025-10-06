package ENTIDADES.PLANODESAUDE;
import ENTIDADES.MEDICO.Especialidades;

public class PlanoDeSaude {
    public static int idPUniversal = 1 ;
    public final int idPlano;
    public String nome;
    public float descontoConsulta;
    public float descontoInternacao;
    private Especialidades especialidade;

    public PlanoDeSaude(String nome, float descontoConsulta, float descontoInternacao, Especialidades especialidade){
        this.idPlano = idPUniversal;
        this.nome = nome;
        this.descontoConsulta = descontoConsulta;
        this.descontoInternacao = descontoInternacao;
        this.especialidade = especialidade;
    }
}
