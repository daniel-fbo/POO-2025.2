package REPOSITORIOS.RPLANODESAUDE;

import ENTIDADES.MEDICO.Especialidades;
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
            System.out.println("ID: " + p.getIdPlano());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Desconto em consultas: " + (1 - p.getDescontoConsulta()) * 100 + "%");
            System.out.println("Desconto em internações: " + (1 - p.getDescontoInternacao()) * 100 + "%");
            System.out.println("Especialidade com desconto: " + p.getEspecialidade());
            System.out.println("--------------------------------------------------");
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
                if (partes.length >= 5) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    double descontoConsulta = Double.parseDouble(partes[2]);
                    double descontoInternacao = Double.parseDouble(partes[3]);
                    Especialidades especialidade = new Especialidades(partes[4]);


                    PlanoDeSaude plano = new PlanoDeSaude(id, nome, descontoConsulta, descontoInternacao, especialidade);
                    listaPlanos.add(plano);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar planos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao interpretar arquivo de planos: " + e.getMessage());
        }
    }


    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (PlanoDeSaude p : listaPlanos) {
                bw.write(
                        p.getIdPlano() + ";" +
                                p.getNome() + ";" +
                                p.getDescontoConsulta() + ";" +
                                p.getDescontoInternacao() + ";" +
                                p.getEspecialidade().getNome()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar planos: " + e.getMessage());
        }
    }

}
