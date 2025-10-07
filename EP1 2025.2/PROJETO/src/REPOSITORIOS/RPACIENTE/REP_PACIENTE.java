package REPOSITORIOS.RPACIENTE;
import ENTIDADES.PACIENTE.Paciente;
import java.util.*;

public interface REP_PACIENTE {
    void salvarPaciente(Paciente paciente);
    Optional<Paciente> buscarCpf(String cpf);
    List<Paciente> listarPacientes();
}
