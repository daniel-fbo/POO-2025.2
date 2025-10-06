package ENTIDADES.PACIENTE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;

public class Paciente {
    public final String nome;
    public final String cpf;
    public short idade;
    public EstadoPaciente estado;
    public Historico historico;
    public PlanoDeSaude plano;

    public Paciente(String nome, String cpf, short idade, EstadoPaciente estado, PlanoDeSaude plano){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.estado = estado;
        this.plano = plano;
        this.historico = null;
    }

    public String getNome(){
        return nome;
    }
}
