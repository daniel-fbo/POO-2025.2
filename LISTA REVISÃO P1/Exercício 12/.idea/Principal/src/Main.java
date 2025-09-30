import Classes.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Livro livro = new Livro("Harry Potter", 350);
        Usuario usuario = new Usuario("Daniel", 18);

        Emprestimo emprestimo = new Emprestimo(usuario, livro,
                LocalDate.of(2025,9,11), LocalDate.of(2025,10,11));


        emprestimo.doEmprestimo();
        emprestimo.doDevolucao();
    }
}
