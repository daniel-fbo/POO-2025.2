package REPOSITORIOS.RPLANODESAUDE;

import ENTIDADES.PLANODESAUDE.PlanoDeSaude;
import java.io.*;
import java.util.*;

public class REP_PLANO_CSV implements REP_PLANO {
    private static final String ARQUIVO = "planos.csv";
    private final List<PlanoDeSaude> listaPlanos = new ArrayList<>();

    public REP_PLANO_CSV() {
        carregarDoArquivo();
    }

    @Override
    public void salvarPlano(PlanoDeSaude plano) {
        listaPlanos.removeIf(p -> p.getIdPlano() == plano.getIdPlano());
        listaPlanos.add(plano);
        salvarNoArquivo();
    }

    @Override
    public Optional<PlanoDeSaude> buscarIdPlano(int idPlano) {
        return listaPlanos.stream()
                .filter(p -> p.getIdPlano() == idPlano)
                .findFirst();
    }

    @Override
    public List<PlanoDeSaude> listarPlanos() {
        return Collections.unmodifiableList(listaPlanos);
    }


    public void imprimirPlanos() {
        if (listaPlanos.isEmpty()) {
            System.out.println("Nenhum plano de saúde cadastrado.");
            return;
        }

        for (PlanoDeSaude p : listaPlanos) {
            System.out.println("ID do Plano: " + p.getIdPlano());
            System.out.println("Nome do Plano: " + p.getNome());
            System.out.println("--------------------------------------------------\n");
        }
    }

    private void carregarDoArquivo() {
        listaPlanos.clear();
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";", -1);
                if (partes.length >= 2) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    listaPlanos.add(new PlanoDeSaude(id, nome));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar planos: " + e.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (PlanoDeSaude p : listaPlanos) {
                bw.write(p.getIdPlano() + ";" + p.getNome());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar planos: " + e.getMessage());
        }
    }
}
