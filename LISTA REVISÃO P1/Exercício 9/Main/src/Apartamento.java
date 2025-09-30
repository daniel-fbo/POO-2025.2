public class Apartamento {
    private static float area;
    private static int quartos;
    private static int andar;
    private static float valorDeCompra;
    private static int vagasDeGaragem;
    private static boolean temVaranda;

    public void exibirInfo(){
        System.out.println(area);
        System.out.println(quartos);
        System.out.println(andar);
        System.out.println(valorDeCompra);
        System.out.println(vagasDeGaragem);
        System.out.println(temVaranda);
    }

    public void setQuartos(int andar){
        this.andar = andar;
    }
    public void setValorDeCompra(float valorDeCompra){
        this.valorDeCompra = valorDeCompra;
    }

    public int getVagasDeGaragem(){
        return vagasDeGaragem;
    }
    public float getArea(){
        return area;
    }
}
