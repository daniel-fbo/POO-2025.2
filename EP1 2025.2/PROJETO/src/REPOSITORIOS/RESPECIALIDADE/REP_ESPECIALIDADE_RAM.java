package REPOSITORIOS.RESPECIALIDADE;
import ENTIDADES.MEDICO.Especialidades;
import java.util.*;


public class REP_ESPECIALIDADE_RAM implements REP_ESPECIALIDADE {
    private static final List<Especialidades> listaEspecialidades = new ArrayList<>();
    static {
            listaEspecialidades.add(new Especialidades("Cardiologia"));
            listaEspecialidades.add(new Especialidades("Cirurgia"));
            listaEspecialidades.add(new Especialidades("Endocrinologia"));
            listaEspecialidades.add(new Especialidades("Oncologia"));
            listaEspecialidades.add(new Especialidades("Oftalmologia"));
            listaEspecialidades.add(new Especialidades("Pediatra"));
            listaEspecialidades.add(new Especialidades("Psiquiatria"));
        }

        @Override
        public void salvarEspecialidade(Especialidades especialidade) {
            listaEspecialidades.removeIf(p -> p.nome.equals(especialidade.nome));
            listaEspecialidades.add(especialidade);
        }

        @Override
        public List<Especialidades> listarEspecialidades() {
            return Collections.unmodifiableList(listaEspecialidades);
        }

}



