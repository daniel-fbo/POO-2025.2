package ENTIDADES.PACIENTE;
import ENTIDADES.PLANODESAUDE.PlanoDeSaude;

import java.time.LocalDate;

public class Paciente_Idoso extends Paciente {
    public LocalDate dataUltimaConsulta;
    public boolean precisaConsulta;

    public Paciente_Idoso(String nome, String cpf, short idade, EstadoPaciente estado, PlanoDeSaude plano, LocalDate dataUltimaConsulta,boolean precisaConsulta){
        super(nome,cpf,idade, estado, plano);
        this.dataUltimaConsulta = dataUltimaConsulta;
        this.precisaConsulta = precisaConsulta;
    }

    public void setDataUltimaConsulta(LocalDate hoje){
        this.dataUltimaConsulta = hoje;
    }

}
