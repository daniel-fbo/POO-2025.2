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
        // Remove médico com mesmo CRM, se existir
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

    public void imprimirMedicos() {
        if (listaMedicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }

        for (Medico m : listaMedicos) {
            System.out.println("Nome: " + m.getNome());
            System.out.println("CRM: " + m.getCrm());
            System.out.println("Especialidade: " + m.getEspecialidade().getNome());
            System.out.println("Custo da Consulta: R$ " + String.format("%.2f", m.getCustoConsulta()));
            System.out.println("--------------------------------------------------\n");
        }
    }

    private void carregarDoArquivo() {
        listaMedicos.clear();
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String primeiraLinha = br.readLine(); // cabeçalho
            if (primeiraLinha == null) return;

            String linha;
            while ((linha = br.readLine()) != null) {
                processarLinha(linha);
            }

        } catch (Exception e) {
            System.err.println("Erro ao carregar médicos do CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processarLinha(String linha) {
        if (linha == null || linha.trim().isEmpty()) return;

        String[] partes = linha.split(";", -1); // separador ;
        if (partes.length < 4) {
            System.err.println("Linha inválida (menos de 4 colunas), pulando: " + linha);
            return;
        }

        try {
            String crm = partes[0].trim();
            String nome = partes[1].trim();
            String especialidadeNome = partes[2].trim();
            String custoStr = partes[3].trim().replace(",", ".");

            if (crm.isEmpty() || nome.isEmpty() || especialidadeNome.isEmpty() || custoStr.isEmpty()) {
                System.err.println("Linha inválida (campos vazios), pulando: " + linha);
                return;
            }

            double custoConsulta = Double.parseDouble(custoStr);

            Especialidades especialidade = new Especialidades(especialidadeNome);

            // Ordem correta: nome, crm, custoConsulta, especialidade
            Medico medico = new Medico(nome, crm, custoConsulta, especialidade);

            listaMedicos.add(medico);
        } catch (Exception ex) {
            System.err.println("Erro ao processar linha do CSV, pulando: " + linha);
            ex.printStackTrace();
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
            System.err.println("Erro ao salvar médicos no CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
