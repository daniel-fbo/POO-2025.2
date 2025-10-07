package ENTIDADES.MEDICO;

public class Especialidades {

/////////////////////   ATRIBUTOS    /////////////////////

    public static int idEUniversal;
    public final int id;
    public String nome;
//////////////////////  CONSTRUTOR   /////////////////////

    public Especialidades(String nome){
        this.id = idEUniversal++;
        this.nome = nome;
    }

/////////////////  GETTERS & SETTERS   //////////////////

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

/////////////////////  MÉTODOS   ///////////////////////

    @Override
    public String toString() {
        return nome;
    }

}
