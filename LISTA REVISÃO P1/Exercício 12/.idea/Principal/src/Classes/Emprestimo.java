package Classes;

import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolução;
    public Livro livro;
    public Usuario cliente;


    public Emprestimo(Usuario cliente, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolução){
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolução = dataDevolução;
    }
    public void doEmprestimo() {
        System.out.println("Empréstimo concluído!:");
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuário: " + cliente.getNome());
        System.out.println("Data de Empréstimo: " + dataEmprestimo);
    }

    public void doDevolucao() {
        System.out.println("Devolução concluída!:");
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuário: " + cliente.getNome());
        System.out.println("Data de Devolução: " + dataDevolução);
    }
}
