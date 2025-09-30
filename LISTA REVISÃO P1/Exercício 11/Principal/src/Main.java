public class Livro{
    private int qtdPaginas;
    private float preço;
    private String título;
}

public class Usuario{
    private String nome;
    private String metodoPagamento;
}

public class Emprestimo{
    private String mesDoEmprestimo;
    private String mesDaDevolução;
    private Usuario cliente;
    private Livro livroEmprestado;
}

