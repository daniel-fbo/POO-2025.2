package REPOSITORIOS.RMEDICO;
import ENTIDADES.MEDICO.Medico;
import java.util.*;


public class REP_MEDICO_RAM implements REP_MEDICO {
    List<Medico> listaMedicos = new ArrayList<>();

    @Override
    public void salvarMedico(Medico medico){
        listaMedicos.removeIf(p -> p.crm.equals(medico.crm));
        listaMedicos.add(medico);

    }

    @Override
    public Optional<Medico> buscarCrm(String crm){
        return listaMedicos.stream()
                .filter(p->p.crm.equals(crm))
                .findFirst();

    }

    @Override
    public List<Medico> listarMedicos(){
        return Collections.unmodifiableList(listaMedicos);

    };

}
