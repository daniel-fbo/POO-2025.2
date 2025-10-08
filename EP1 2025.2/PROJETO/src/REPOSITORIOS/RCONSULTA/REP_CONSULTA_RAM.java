package REPOSITORIOS.RCONSULTA;
import PROCESSOS.CONSULTAS.Consulta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class REP_CONSULTA_RAM implements REP_CONSULTA {
    List<Consulta> listaConsultas = new ArrayList<>();

    @Override
    public void salvar(Consulta consulta) {
        listaConsultas.removeIf(c -> c.idConsulta == consulta.idConsulta);
        listaConsultas.add(consulta);
    }

    @Override
    public Optional<Consulta> buscarIdConsulta(int idConsulta) {
        return listaConsultas.stream()
                .filter(c->c.idConsulta == idConsulta)
                .findFirst();
    }

    @Override
    public List<Consulta> listarConsultas() {
        return Collections.unmodifiableList(listaConsultas);
    }
    public void exportarCSV(String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            // Cabeçalho do CSV (mude conforme atributos da Consulta)
            writer.append("IdConsulta,Paciente,Medico,HorarioConsulta,CustoFinal\n");

            for (Consulta c : listaConsultas) {
                writer.append(String.valueOf(c.getIdConsulta())).append(",");
                writer.append(c.getPaciente().getNome()).append(","); // Assumindo getNome()
                writer.append(c.getMedico().getNome()).append(",");   // Assumindo getNome()
                writer.append(c.getHorarioConsulta().toString()).append(",");
                writer.append(String.valueOf(c.getCustoFinal())).append("\n");
            }

            System.out.println("CSV exportado com sucesso para: " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao exportar CSV: " + e.getMessage());
        }
    }
}

