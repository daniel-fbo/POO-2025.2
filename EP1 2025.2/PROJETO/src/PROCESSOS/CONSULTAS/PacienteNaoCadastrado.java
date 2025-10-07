package PROCESSOS.CONSULTAS;

public class PacienteNaoCadastrado extends Exception {
  public PacienteNaoCadastrado(String nome) {
    super("O paciente \"" + nome + "\" não está cadastrado no sistema.");
  }
}
