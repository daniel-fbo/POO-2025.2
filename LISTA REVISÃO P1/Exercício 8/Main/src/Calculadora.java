public class Calculadora {
    public float media(float n1, float n2, float n3) {
        float media;
        media = (n1 + n2 + n3) / 3;
        return media;
    }
}

public class CalculadoraAvancada extends Calculadora{

    @Override
    public float media(float n1, float n2, float n3){
        float media;
        media = (n1 + (n2 * 2) + (n3 * 3))/ 6;
        return media;
    }
}
