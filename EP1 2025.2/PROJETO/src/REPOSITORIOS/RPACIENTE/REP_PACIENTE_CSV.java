package REPOSITORIOS.RPACIENTE;

import ENTIDADES.PACIENTE.Paciente;
import java.io.*;
import java.util.*;

public class REP_PACIENTE_CSV implements REP_PACIENTE {
    private static final String ARQUIVO = "pacientes.csv";
    private final List<Paciente> listaPacientes = new ArrayList<>();

    public REP_PACIENTE_CSV() {
        carregarDoArquivo();
    }

    @Override
    public void salvarPaciente(Paciente paciente) {
        listaPacientes.removeIf(p -> p.getCpf().equals(paciente.getCpf()));
        listaPacientes.add(paciente);
        salvarNoArquivo();
    }

    @Override
    public Optional<Paciente> buscarNome(String nome) {
        return listaPacientes.stream()
                .filter(p -> p.getNome().equals(nome))
                .findFirst();
    }

    @Override
    public boolean isntCadastrado(String nome) {
        return listaPacientes.stream()
                .noneMatch(p -> p.getNome().equals(nome));
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

    // ------------------------
    // Persistência CSV
    // ------------------------
    private void carregarDoArquivo() {
        listaPacientes.clear();
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String primeiraLinha = br.readLine(); // cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] partes = linha.split(";", -1);
                if (partes.length < 3) continue; // inválido

                String nome = partes[0].trim();
                String cpf = partes[1].trim();

                Paciente paciente = new Paciente(nome, cpf);
                listaPacientes.add(paciente);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar pacientes: " + e.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
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
}
