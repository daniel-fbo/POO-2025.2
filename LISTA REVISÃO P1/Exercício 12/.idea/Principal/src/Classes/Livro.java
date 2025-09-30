package Classes;

public class Livro{
    private int qtdPaginas;
    public String titulo;

    public Livro(String titulo, int qtdPaginas){
        this.qtdPaginas = qtdPaginas;
        this.titulo = titulo;
    }

    public String getTitulo(){
        return titulo;
    }
}



