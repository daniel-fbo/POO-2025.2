package ENTIDADES.PLANODESAUDE;

public class PlanoDeSaude {
    private String nome;
    private float descontoConsulta;
    private float descontoInternacao;

    public PlanoDeSaude(String nome, float descontoConsulta, float descontoInternacao){
        this.nome = nome;
        this.descontoConsulta = descontoConsulta;
        this.descontoInternacao = descontoInternacao;
    }
}
