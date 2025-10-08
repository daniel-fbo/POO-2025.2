package REPOSITORIOS.RESPECIALIDADE;

import ENTIDADES.MEDICO.Especialidades;
import java.io.*;
import java.util.*;

public class REP_ESPECIALIDADE_CSV implements REP_ESPECIALIDADE {
    private static final String ARQUIVO = "especialidades.csv";
    private final List<Especialidades> listaEspecialidades = new ArrayList<>();

    public REP_ESPECIALIDADE_CSV() {
        carregarDoArquivo();
    }

    @Override
    public void salvarEspecialidade(Especialidades especialidade) {
        // Remove duplicata (mesmo nome)
        listaEspecialidades.removeIf(e -> e.getNome().equalsIgnoreCase(especialidade.getNome()));
        listaEspecialidades.add(especialidade);
        salvarNoArquivo();
    }

    @Override
    public List<Especialidades> listarEspecialidades() {
        return Collections.unmodifiableList(listaEspecialidades);
    }

    // ------------------------------------------------------
    // 🔽 Métodos auxiliares
    // ------------------------------------------------------

    private void carregarDoArquivo() {
        listaEspecialidades.clear();
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                listaEspecialidades.add(new Especialidades(linha.trim()));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar especialidades: " + e.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Especialidades e : listaEspecialidades) {
                bw.write(e.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar especialidades: " + e.getMessage());
        }
    }
}
