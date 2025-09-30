public class Cachorro {
    // Atributos da classse
    private static int qtdPatas;
    private static float peso;
    private static boolean estaDoente;
    private static String cor;

    //Metodo construtor da classse

    Cachorro(int qtdPatas, float peso, boolean estaDoente, String cor) {
        this.qtdPatas = qtdPatas;
        this.peso = peso;
        this.estaDoente = estaDoente;
        this.cor = cor;
    }
}


// Objeto com os atributos da classe
Cachorro Princesa = new Cachorro(4, 45.5, 0, "preta" );


