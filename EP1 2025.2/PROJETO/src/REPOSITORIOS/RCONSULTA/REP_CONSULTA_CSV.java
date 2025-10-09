package REPOSITORIOS.RCONSULTA;

import ENTIDADES.MEDICO.Medico;
import ENTIDADES.PACIENTE.Paciente;
import PROCESSOS.CONSULTAS.Consulta;
import REPOSITORIOS.RMEDICO.REP_MEDICO_CSV;
import REPOSITORIOS.RPACIENTE.REP_PACIENTE_CSV;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class REP_CONSULTA_CSV implements REP_CONSULTA {
    private final String caminhoArquivo = "consultas.csv";
    private final List<Consulta> listaConsultas = new ArrayList<>();
    private final REP_PACIENTE_CSV rPaciente = new REP_PACIENTE_CSV();
    private final REP_MEDICO_CSV rMedico = new REP_MEDICO_CSV();

    public REP_CONSULTA_CSV() {
        carregarDoArquivo();
    }


    @Override
    public void salvarConsulta(Consulta consulta) {
        listaConsultas.add(consulta);
        salvarNoArquivo();
    }

    @Override
    public void atualizarConsulta(Consulta consulta) {
        listaConsultas.removeIf(c -> c.getIdConsulta() == consulta.getIdConsulta());
        listaConsultas.add(consulta);
        salvarNoArquivo();
    }

    @Override
    public Optional<Consulta> buscarIdConsulta(int id) {
        return listaConsultas.stream().filter(c -> c.getIdConsulta() == id).findFirst();
    }

    @Override
    public List<Consulta> listarConsultas() {
        return new ArrayList<>(listaConsultas);
    }


    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Cabeçalho
            bw.write("idConsulta;cpfPaciente;crmMedico;especialidade;horario;status;custo;diagnostico");
            bw.newLine();

            for (Consulta c : listaConsultas) {
                bw.write(c.getRelatorio().toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar consultas: " + e.getMessage());
        }
    }


    private void carregarDoArquivo() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length < 5) continue;

                int id = Integer.parseInt(partes[0]);
                String cpf = partes[1];
                String crm = partes[2];
                LocalDateTime horario = LocalDateTime.parse(partes[3]);
                double custo = Double.parseDouble(partes[4]);

                Optional<Paciente> oPaciente = rPaciente.buscarCpf(cpf);
                Optional<Medico> oMedico = rMedico.buscarCrm(crm);
                if (oPaciente.isEmpty() || oMedico.isEmpty()) continue;

                Consulta consulta = new Consulta(id, oPaciente.get(), oMedico.get());
                consulta.setHorarioConsulta(horario);
                consulta.setCustoFinal(custo);

                listaConsultas.add(consulta);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar consultas: " + e.getMessage());
        }
    }
}
