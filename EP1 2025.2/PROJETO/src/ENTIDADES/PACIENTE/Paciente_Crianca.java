package ENTIDADES.PACIENTE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;

public class Paciente_Crianca extends Paciente {
    public String nomeResponsavel;
    public String cpfResponsavel;


    public Paciente_Crianca(String nome, String cpf, String nomeResponsavel, String cpfResponsavel, short idade, EstadoPaciente estado, PlanoDeSaude plano){
        super(nome,cpf,idade,estado,plano);
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
    }

}
