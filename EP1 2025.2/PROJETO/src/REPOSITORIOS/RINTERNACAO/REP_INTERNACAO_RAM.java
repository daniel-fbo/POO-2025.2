package REPOSITORIOS.RINTERNACAO;
import PROCESSOS.CONSULTAS.Status;
import PROCESSOS.INTERNACAO.Internacao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class REP_INTERNACAO_RAM implements REP_INTERNACAO {
    List<Internacao> listaInternacoes = new ArrayList<>();

    @Override
    public void salvar(Internacao internacao){
        listaInternacoes.removeIf(i -> i.idInternacao ==(internacao.idInternacao));
        listaInternacoes.add(internacao);
    }

    @Override
    public Optional<Internacao> buscarCpf(String cpf){
        return listaInternacoes.stream()
                .filter(i -> i.paciente.cpf.equals(cpf))
                .findFirst();
    }

    @Override
    public Optional<Internacao> buscarCpfAtivo(String cpf){
        return listaInternacoes.stream()
                .filter(i ->i.status == Status.EM_PROCESSO)
                .filter(i -> i.paciente.cpf.equals(cpf))
                .findFirst();
    }

    @Override
    public List<Internacao> listarInternacoes(){
        return Collections.unmodifiableList(listaInternacoes);
    }
}
