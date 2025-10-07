package ENTIDADES.PACIENTE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;

public class Paciente_Idoso extends Paciente {
    public Paciente_Idoso(String nome, String cpf, short idade, EstadoPaciente estado, PlanoDeSaude plano){
        super(nome,cpf,idade, estado, plano);
    }

}
