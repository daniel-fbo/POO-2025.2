package ENTIDADES.PACIENTE;

public class Paciente {
    public String nome;
    public String cpf;
    public short idade;
    public Historico historico;

    public Paciente(String nome, String cpf, short idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }
    public void setHistorico(Historico novoHistorico) {
        this.historico = novoHistorico;
    }
    public Historico getHistorico(){
        return historico;
    }
}
