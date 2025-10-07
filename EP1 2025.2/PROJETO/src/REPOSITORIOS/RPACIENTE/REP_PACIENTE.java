package REPOSITORIOS.RPACIENTE;
import ENTIDADES.PACIENTE.Paciente;
import java.util.*;

public interface REP_PACIENTE {
    void salvarPaciente(Paciente paciente);
    boolean isntCadastrado(String nome);
    Optional<Paciente> buscarCpf(String cpf);
    Optional<Paciente> buscarNome(String cpf);
    List<Paciente> listarPacientes();
}
