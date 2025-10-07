package PROCESSOS.CONSULTAS;

public record Diagnostico(
        String descricao,
        String medicacoesSugeridas,
        String examesRecomendados
) {

    @Override
    public String toString() {
        return String.format(
                "--- Diagnóstico ---%n" +
                        "Descrição: %s%n" +
                        "Medicações Sugeridas: %s%n" +
                        "Exames Recomendados: %s",
                descricao,
                medicacoesSugeridas,
                examesRecomendados
        );
    }
}