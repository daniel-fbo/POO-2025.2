/*package REPOSITORIOS.RMEDICO;
import ENTIDADES.MEDICO.*;
import java.util.*;


public class REP_MEDICO_RAM implements REP_MEDICO {
    List<Medico> listaMedicos = new ArrayList<>();

    @Override
    public void salvarMedico(Medico medico){
        listaMedicos.removeIf(p -> p.crm.equals(medico.crm));
        listaMedicos.add(medico);

    }

    @Override
    public Optional<Medico> buscarCrm(String crmString) {
        try {
            short crmNumero = Short.parseShort(crmString);
            return listaMedicos.stream()
                    .filter(medico -> String.valueOf(medico.getCrm()).equals(String.valueOf(crmNumero)))
                    .findFirst();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Medico> buscarEspecialidade(Especialidades especialidade){
        return listaMedicos.stream()
                .filter(p->p.especialidade.equals(especialidade))
                .findFirst();

    }

    @Override
    public List<Medico> listarMedicos(){
        return Collections.unmodifiableList(listaMedicos);

    }

}*/
