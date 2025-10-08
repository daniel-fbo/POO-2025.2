package REPOSITORIOS.RPACIENTE;

import ENTIDADES.PACIENTE.Paciente;
import ENTIDADES.PACIENTE.Historico;
import PROCESSOS.CONSULTAS.RelatorioConsulta;
import PROCESSOS.CONSULTAS.Diagnostico;
import PROCESSOS.INTERNACAO.RelatorioInternacao;
import PROCESSOS.INTERNACAO.Leito;
import PROCESSOS.INTERNACAO.TipoLeito;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class REP_PACIENTE_CSV implements REP_PACIENTE {

    private static final String ARQUIVO_PACIENTES = "pacientes.csv";
    private static final String ARQUIVO_HIST_CONSULTAS = "historico_consultas.csv";
    private static final String ARQUIVO_HIST_INTERNACOES = "historico_internacoes.csv";

    private final List<Paciente> listaPacientes = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public REP_PACIENTE_CSV() {
        carregarPacientes();
        carregarHistoricos();
    }

    // ------------------------
    // CRUD Paciente
    // ------------------------
    @Override
    public void salvarPaciente(Paciente paciente) {
        listaPacientes.removeIf(p -> p.getCpf().equals(paciente.getCpf()));
        listaPacientes.add(paciente);
        salvarPacientes();
        salvarHistoricos(paciente);
    }

    @Override
    public Optional<Paciente> buscarNome(String nome) {
        return listaPacientes.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    @Override
    public boolean isntCadastrado(String nome) {
        return listaPacientes.stream()
                .noneMatch(p -> p.getNome().equalsIgnoreCase(nome));
    }

    @Override
    public Optional<Paciente> buscarCpf(String cpf) {
        return listaPacientes.stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst();
    }

    @Override
    public List<Paciente> listarPacientes() {
        return Collections.unmodifiableList(listaPacientes);
    }

    public void imprimirPacientes() {
        if (listaPacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        for (Paciente p : listaPacientes) {
            System.out.println("Nome: " + p.nome);
            System.out.println("CPF: " + p.cpf);
            System.out.println("Idade: " + p.idade);

            if (p.getPlano() != null) {
                System.out.println("Plano de Saúde: " + p.getPlano().getNome());
            } else {
                System.out.println("Plano de Saúde: Não possui");
            }

            // Histórico completo
            if (p.getHistorico() != null) {
                System.out.println(p.getHistorico().toString());
            } else {
                System.out.println("Histórico: vazio");
            }

            System.out.println("--------------------------------------------------\n");
        }
    }


    private void carregarPacientes() {
        listaPacientes.clear();
        File file = new File(ARQUIVO_PACIENTES);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";", -1);
                if (partes.length < 2) continue;
                String nome = partes[0].trim();
                String cpf = partes[1].trim();
                listaPacientes.add(new Paciente(nome, cpf)); // Historico inicializado
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar pacientes: " + e.getMessage());
        }
    }

    private void salvarPacientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_PACIENTES))) {
            bw.write("Nome;CPF");
            bw.newLine();
            for (Paciente p : listaPacientes) {
                bw.write(p.getNome() + ";" + p.getCpf());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar pacientes: " + e.getMessage());
        }
    }

    private void carregarHistoricoConsultas() {
        File file = new File(ARQUIVO_HIST_CONSULTAS);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";", -1);
                if (partes.length < 8) continue;

                String cpfPaciente = partes[1].trim();
                Optional<Paciente> paciente = buscarCpf(cpfPaciente);
                if (paciente.isEmpty()) continue;

                int idConsulta = Integer.parseInt(partes[0].trim());
                String crmMedico = partes[2].trim();
                LocalDateTime horario = LocalDateTime.parse(partes[3].trim());
                String especialidade = partes[4].trim();
                double custo = Double.parseDouble(partes[5].trim().replace(",", "."));
                String descDiag = partes[6].trim();
                String meds = partes[7].trim();
                String exames = partes[8].trim();

                Diagnostico diag = new Diagnostico(descDiag, meds, exames);
                RelatorioConsulta relatorio = new RelatorioConsulta(
                        idConsulta,
                        paciente.get(),
                        null, // médico pode ser buscado pelo CRM em outro repositório
                        horario,
                        null,
                        null, // especialidade pode ser reconstruída se necessário
                        custo,
                        diag
                );
                paciente.get().getHistorico().adicionarConsultaHistorico(relatorio);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar histórico de consultas: " + e.getMessage());
        }
    }

    private void salvarHistoricoConsulta(Paciente paciente, RelatorioConsulta relatorio) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_HIST_CONSULTAS, true))) {
            bw.write(String.join(";",
                    String.valueOf(relatorio.idConsulta()),
                    paciente.getCpf(),
                    "", // CRM médico (opcional)
                    relatorio.horarioConsulta().toString(),
                    relatorio.especialidade() != null ? relatorio.especialidade().toString() : "",
                    String.valueOf(relatorio.custoFinal()),
                    relatorio.diagnostico().descricao(),
                    relatorio.diagnostico().medicacoesSugeridas(),
                    relatorio.diagnostico().examesRecomendados()
            ));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico de consultas: " + e.getMessage());
        }
    }


    private void carregarHistoricoInternacoes() {
        File file = new File(ARQUIVO_HIST_INTERNACOES);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";", -1);
                if (partes.length < 6) continue;

                String cpfPaciente = partes[1].trim();
                Optional<Paciente> paciente = buscarCpf(cpfPaciente);
                if (paciente.isEmpty()) continue;

                int idInternacao = Integer.parseInt(partes[0].trim());
                short idLeito = Short.parseShort(partes[2].trim());
                String tipoLeitoStr = partes[3].trim();
                TipoLeito tipoLeitoEnum;

                try {
                    tipoLeitoEnum = TipoLeito.valueOf(tipoLeitoStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    tipoLeitoEnum = TipoLeito.QUARTO;
                }

                Leito leito = new Leito(idLeito, tipoLeitoEnum);

                Duration duracao = Duration.ofHours(Long.parseLong(partes[4].trim()));
                double custo = Double.parseDouble(partes[5].trim().replace(",", "."));

                RelatorioInternacao relatorio = new RelatorioInternacao(idInternacao, paciente.get(), leito, duracao, custo);
                paciente.get().getHistorico().adicionarInternacaoHistorico(relatorio);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar histórico de internações: " + e.getMessage());
        }
    }


    private void salvarHistoricoInternacao(Paciente paciente, RelatorioInternacao relatorio) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_HIST_INTERNACOES, true))) {
            bw.write(String.join(";",
                    String.valueOf(relatorio.idInternacao()),
                    paciente.getCpf(),
                    String.valueOf(relatorio.leito().getIdLeito()),
                    String.valueOf(relatorio.duracao().toHours()),
                    String.valueOf(relatorio.custoFinal())
            ));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico de internações: " + e.getMessage());
        }
    }

    private void carregarHistoricos() {
        carregarHistoricoConsultas();
        carregarHistoricoInternacoes();
    }

    public void salvarHistoricos(Paciente paciente) {
        Historico h = paciente.getHistorico();
        for (RelatorioConsulta rc : h.historicoConsultas) salvarHistoricoConsulta(paciente, rc);
        for (RelatorioInternacao ri : h.historicoInternacao) salvarHistoricoInternacao(paciente, ri);
    }

    public void adicionarConsulta(Paciente paciente, RelatorioConsulta relatorio) {
        paciente.getHistorico().adicionarConsultaHistorico(relatorio);
        salvarHistoricoConsulta(paciente, relatorio);
    }

    public void adicionarInternacao(Paciente paciente, RelatorioInternacao relatorio) {
        paciente.getHistorico().adicionarInternacaoHistorico(relatorio);
        salvarHistoricoInternacao(paciente, relatorio);
    }
}
