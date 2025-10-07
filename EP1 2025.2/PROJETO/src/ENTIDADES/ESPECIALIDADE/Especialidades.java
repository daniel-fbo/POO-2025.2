package ENTIDADES.ESPECIALIDADE;

public class Especialidades {
    private static int idEUniversal;
    private final int id;
    private String nome;

    public Especialidades(String nome){
        this.id = idEUniversal++;
        this.nome = nome;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
