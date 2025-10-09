package REPOSITORIOS.RINTERNACAO;

import PROCESSOS.CONSULTAS.Status;
import PROCESSOS.INTERNACAO.Internacao;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class REP_INTERNACAO_CSV implements REP_INTERNACAO {
    private static final String ARQUIVO = "internacoes.csv";
    private final List<Internacao> listaInternacoes = new ArrayList<>();

    public REP_INTERNACAO_CSV() {
        carregarDoArquivo();
    }


    @Override
    public void salvarInternacao(Internacao internacao) {
        listaInternacoes.add(internacao);
        salvarNoArquivo();
    }

    // Atualizar internação existente com alta
    @Override
    public void atualizarAlta(Internacao internacao) {
        listaInternacoes.removeIf(i -> i.getIdInternacao() == internacao.getIdInternacao());
        listaInternacoes.add(internacao);
        salvarNoArquivo();
    }

    // ======= MÉTODOS DE BUSCA =======
    @Override
    public Optional<Internacao> buscarCpf(String cpf) {
        return listaInternacoes.stream()
                .filter(i -> i.getPaciente().getCpf().equals(cpf))
                .findFirst();
    }

    @Override
    public Optional<Internacao> buscarCpfAtivo(String cpf) {
        return listaInternacoes.stream()
                .filter(i -> i.getStatus() == Status.EM_PROCESSO)
                .filter(i -> i.getPaciente().getCpf().equals(cpf))
                .findFirst();
    }

    @Override
    public List<Internacao> listarInternacoes() {
        return Collections.unmodifiableList(listaInternacoes);
    }

    // ======= MÉTODOS PRIVADOS =======
    private void carregarDoArquivo() {
        listaInternacoes.clear();
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha = br.readLine(); // pula cabeçalho
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";", -1);
                if (partes.length >= 6) {
                    int id = Integer.parseInt(partes[0]);
                    String nomePaciente = partes[1];
                    String cpf = partes[2];
                    LocalDateTime data = LocalDateTime.parse(partes[3]);
                    Status status = Status.valueOf(partes[4]);

                    Internacao internacao = new Internacao(id, nomePaciente, cpf, data, status);
                    listaInternacoes.add(internacao);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar internações: " + e.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            bw.write("IdInternacao;Paciente;CPF;DataInternacao;Status");
            bw.newLine();

            for (Internacao i : listaInternacoes) {
                bw.write(i.getIdInternacao() + ";" +
                        i.getPaciente().getNome() + ";" +
                        i.getPaciente().getCpf() + ";" +
                        i.getDataInternacao() + ";" +
                        i.getStatus().name());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar internações: " + e.getMessage());
        }
    }
}
