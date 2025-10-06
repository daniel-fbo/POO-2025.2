package REPOSITORIOS.RPACIENTE;
import ENTIDADES.PACIENTE.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class REP_PACIENTE_RAM implements REP_PACIENTE {
    private static final List<Paciente> listaPacientes = new ArrayList<>();

    @Override
    public void salvarPaciente(Paciente paciente){
        listaPacientes.removeIf(p -> p.cpf.equals(paciente.cpf) );
        listaPacientes.add(paciente);
    }

    @Override
    public Optional<Paciente> buscarCpf(String cpf){
        return listaPacientes.stream()
                .filter(p -> p.cpf.equals(cpf))
                .findFirst();
    }

    @Override
    public List<Paciente> listarPacientes(){
        return new ArrayList<>(listaPacientes);
    }


}
