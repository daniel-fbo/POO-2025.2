package ENTIDADES.MEDICO;

public class Especialidades {
    public static int idEUniversal;
    public final int id;
    public String nome;

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
