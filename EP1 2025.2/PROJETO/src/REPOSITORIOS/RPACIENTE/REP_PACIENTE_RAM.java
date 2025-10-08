/*package REPOSITORIOS.RPACIENTE;
import ENTIDADES.PACIENTE.Paciente;
import java.util.*;

public class REP_PACIENTE_RAM implements REP_PACIENTE {
    private static final List<Paciente> listaPacientes = new ArrayList<>();

    @Override
    public void imprimirPacientes();

    @Override
    public void salvarPaciente(Paciente paciente){
        listaPacientes.removeIf(p -> p.cpf.equals(paciente.cpf) );
        listaPacientes.add(paciente);
    }

    @Override
    public Optional<Paciente> buscarNome(String nome){
        return listaPacientes.stream()
                .filter(p -> p.nome.equals(nome))
                .findFirst();
    }

    @Override
    public boolean isntCadastrado(String nome){
        return listaPacientes.stream()
                .noneMatch(n -> n.nome.equals(nome));
    }

    @Override
    public Optional<Paciente> buscarCpf(String cpf){
        return listaPacientes.stream()
                .filter(p -> p.cpf.equals(cpf))
                .findFirst();
    }

    @Override
    public List<Paciente> listarPacientes(){
        return Collections.unmodifiableList(listaPacientes);
    }

}*/
