package ENTIDADES.PACIENTE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;

public class Paciente_Crianca extends Paciente {
    public Paciente_Crianca(String nome, String cpf, short idade, EstadoPaciente estado, PlanoDeSaude plano){
        super(nome,cpf,idade,estado,plano);
    }
}
