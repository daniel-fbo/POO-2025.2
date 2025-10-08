package REPOSITORIOS.RCONSULTA;

import PROCESSOS.CONSULTAS.Consulta;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class REP_CONSULTA_CSV implements REP_CONSULTA {
    private static final String ARQUIVO = "consultas.csv";
    private final List<Consulta> listaConsultas = new ArrayList<>();

    public REP_CONSULTA_CSV() {
        carregarDoArquivo();
    }

    @Override
    public void salvar(Consulta consulta) {
        listaConsultas.removeIf(c -> c.getIdConsulta() == consulta.getIdConsulta());
        listaConsultas.add(consulta);
        salvarNoArquivo();
    }

    @Override
    public Optional<Consulta> buscarIdConsulta(int idConsulta) {
        return listaConsultas.stream()
                .filter(c -> c.getIdConsulta() == idConsulta)
                .findFirst();
    }

    @Override
    public List<Consulta> listarConsultas() {
        return Collections.unmodifiableList(listaConsultas);
    }

    private void carregarDoArquivo() {
        listaConsultas.clear();
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha = br.readLine();
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";", -1);
                if (partes.length >= 5) {
                    int id = Integer.parseInt(partes[0]);
                    String nomePaciente = partes[1];
                    String nomeMedico = partes[2];
                    LocalDateTime horario = LocalDateTime.parse(partes[3]);
                    double custo = Double.parseDouble(partes[4]);

                    Consulta consulta = new Consulta(id, nomePaciente, nomeMedico, horario, custo);
                    listaConsultas.add(consulta);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar consultas: " + e.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            bw.write("IdConsulta;Paciente;Medico;HorarioConsulta;CustoFinal");
            bw.newLine();

            for (Consulta c : listaConsultas) {
                bw.write(c.getIdConsulta() + ";" +
                        c.getPaciente().getNome() + ";" +
                        c.getMedico().getNome() + ";" +
                        c.getHorarioConsulta() + ";" +
                        c.getCustoFinal());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar consultas: " + e.getMessage());
        }
    }
}
