package REPOSITORIOS.RMEDICO;

import ENTIDADES.MEDICO.Especialidades;
import ENTIDADES.MEDICO.Medico;

import java.io.*;
import java.util.*;

public class REP_MEDICO_CSV implements REP_MEDICO {
    private static final String ARQUIVO = "medicos.csv";
    private final List<Medico> listaMedicos = new ArrayList<>();

    public REP_MEDICO_CSV() {
        carregarDoArquivo();
    }

    @Override
    public void salvarMedico(Medico medico) {
        listaMedicos.removeIf(m -> m.getCrm().equals(medico.getCrm()));
        listaMedicos.add(medico);
        salvarNoArquivo();
    }

    @Override
    public Optional<Medico> buscarCrm(String crm) {
        return listaMedicos.stream()
                .filter(m -> m.getCrm().equals(crm))
                .findFirst();
    }

    @Override
    public Optional<Medico> buscarEspecialidade(Especialidades especialidade) {
        return listaMedicos.stream()
                .filter(m -> m.getEspecialidade().equals(especialidade))
                .findFirst();
    }

    @Override
    public List<Medico> listarMedicos() {
        return Collections.unmodifiableList(listaMedicos);
    }

    // -------------------------
    // Persistência
    // -------------------------

    private void carregarDoArquivo() {
        listaMedicos.clear();
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String primeiraLinha = br.readLine();
            if (primeiraLinha == null) return;

            boolean temCabecalho = primeiraLinha.toLowerCase().startsWith("crm") || primeiraLinha.contains("especialidade");
            if (!temCabecalho) processarLinha(primeiraLinha);

            String linha;
            while ((linha = br.readLine()) != null) {
                processarLinha(linha);
            }

        } catch (Exception e) {
            System.err.println("Erro ao carregar médicos: " + e.getMessage());
        }
    }

    private void processarLinha(String linha) {
        if (linha == null || linha.trim().isEmpty()) return;

        String[] partes = linha.split(";", -1);
        if (partes.length < 4) return; // inválido

        try {
            String crm = partes[0].trim();
            String nome = partes[1].trim();
            String especialidadeNome = partes[2].trim();
            double custoConsulta = Double.parseDouble(partes[3].trim().replace(",", "."));

            Especialidades especialidade = new Especialidades(especialidadeNome);
            Medico medico = new Medico(crm, nome, custoConsulta, especialidade);

            listaMedicos.add(medico);
        } catch (Exception ex) {
            System.err.println("Linha inválida de médico (pulando): " + linha + " — erro: " + ex.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            bw.write("CRM;Nome;Especialidade;CustoConsulta");
            bw.newLine();

            for (Medico m : listaMedicos) {
                bw.write(m.getCrm() + ";" +
                        m.getNome() + ";" +
                        m.getEspecialidade().getNome() + ";" +
                        String.valueOf(m.getCustoConsulta()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar médicos: " + e.getMessage());
        }
    }
}
