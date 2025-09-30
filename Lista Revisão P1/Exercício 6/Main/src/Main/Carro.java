package Main;

public class Carro extends Veiculo {
    private static int qtdAssentos;
    private static boolean temCadeiraInfantil;
    private float tamanhoVolante;

    //Metodo construtor ja entende os atributos herdados de Veiculo

    public Carro (String cor, float valor, int qtdAssentos, boolean temCadeiraInfantil,float tamanhoVolante){
        this.cor = cor;
        this.valor = valor;
        this.qtdAssentos = qtdAssentos;
        this.temCadeiraInfantil = temCadeiraInfantil;
        this.tamanhoVolante = tamanhoVolante;
    }

}
