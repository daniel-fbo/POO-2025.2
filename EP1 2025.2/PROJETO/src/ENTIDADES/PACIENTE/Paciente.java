package ENTIDADES.PACIENTE;

public class Paciente {
    public final String nome;
    public final String cpf;
    public short idade;
    public EstadoPaciente estado;
    public Historico historico;

    public Paciente(String nome, String cpf, short idade, EstadoPaciente estado){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.estado = estado;
    }
}
