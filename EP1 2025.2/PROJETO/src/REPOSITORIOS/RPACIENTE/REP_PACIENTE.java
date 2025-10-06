package REPOSITORIOS.RPACIENTE;
import ENTIDADES.PACIENTE.Paciente;
import java.util.List;

public interface REP_PACIENTE {
    void salvar(Paciente paciente);
    Paciente buscarCpf(String cpf);
    List<Paciente> listarTodos();
}
