package PROCESSOS.INTERNACAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GerenciadorLeitos {

/////////////////////   ATRIBUTOS    /////////////////////

    private final List<Leito> quartos;
    private final List<Leito> salasAmarelas;
    private final List<Leito> salasVermelhas;

//////////////////////  CONSTRUTOR   /////////////////////

    public GerenciadorLeitos(short qtdQuartos, short qtdSalasAmarelas, short qtdSalasVermelhas,
                             double custoQuarto, double custoSalaAmarela, double custoSalaVermelha) {
        this.quartos = new ArrayList<>();
        this.salasAmarelas = new ArrayList<>();
        this.salasVermelhas = new ArrayList<>();

        inicializarLeitos(qtdQuartos, TipoLeito.QUARTO, custoQuarto, quartos);
        inicializarLeitos(qtdSalasAmarelas, TipoLeito.SALA_AMARELA,  custoSalaAmarela, salasAmarelas);
        inicializarLeitos(qtdSalasVermelhas, TipoLeito.SALA_VERMELHA, custoSalaVermelha, salasVermelhas);
    }

/////////////////  GETTERS & SETTERS   //////////////////

    private List<Leito> getListaPeloTipo(TipoLeito tipo) {
        return switch (tipo) {
            case QUARTO -> quartos;
            case SALA_AMARELA -> salasAmarelas;
            case SALA_VERMELHA -> salasVermelhas;
        };
    }

    public Optional<Leito> getLeitoDisponivel(TipoLeito tipo) {
        return getListaPeloTipo(tipo)
                .stream()
                .filter(leito -> !leito.isOcupado())
                .findFirst();
    }

/////////////////////  MÉTODOS   ///////////////////////

    private void inicializarLeitos(short quantidade, TipoLeito tipo, double custoDiario, List<Leito> destino) {
        for (short i = 1; i <= quantidade; i++) {
            destino.add(new Leito(i, tipo, custoDiario));
        }
    }

    public void listarLeitosPorTipo(TipoLeito tipo) {
        List<Leito> listaLeitosTipo = getListaPeloTipo(tipo);
        if (listaLeitosTipo.isEmpty()) {
            System.out.println("Não há " + tipo);
        } else {
            System.out.println("Quantidade de " + tipo + ":");
            listaLeitosTipo.forEach(System.out::println);
        }
    }

    public long contarOcupados(TipoLeito tipo) {
        return getListaPeloTipo(tipo).stream()
                .filter(Leito::isOcupado)
                .count();
    }

    private long contarLivres(TipoLeito tipo) {
        return getListaPeloTipo(tipo).stream()
                .filter(leito -> !leito.isOcupado())
                .count();
    }

    @Override
    public String toString() {
        String string =
                "Total Leitos:" + "\n" +
                "  >>Quartos - " + quartos.size() + "\n" +
                "    Ocupados: " +contarOcupados(TipoLeito.QUARTO) +
                " Livres:" + contarLivres(TipoLeito.QUARTO) + "\n" +
                "  >>Salas Amarelas - " + salasAmarelas.size() + "\n" +
                "    Ocupados: " + contarOcupados(TipoLeito.SALA_VERMELHA) +
                " Livres:" +contarLivres(TipoLeito.SALA_AMARELA) + "\n" +
                "  >>Salas Vermelhas - " + salasVermelhas.size() + "\n" +
                "    Ocupados: " +contarOcupados(TipoLeito.SALA_VERMELHA) +
                " Livres:" + contarLivres(TipoLeito.SALA_VERMELHA);

        return string;
    }

}
