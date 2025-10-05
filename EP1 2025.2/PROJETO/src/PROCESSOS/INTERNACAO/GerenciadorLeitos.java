package PROCESSOS.INTERNACAO;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorLeitos {
    private List<Leito> quartos;
    private List<Leito> salasA;
    private List<Leito> salasV;

    public GerenciadorLeitos(int qtdQuartos, int qtdSalasA, int qtdSalasV) {
        quartos = new ArrayList<>();
        salasA = new ArrayList<>();
        salasV = new ArrayList<>();

        inicializarLeitos(qtdQuartos, TipoLeito.QUARTO, quartos);
        inicializarLeitos(qtdSalasA, TipoLeito.SALA_AMARELA, salasA);
        inicializarLeitos(qtdSalasV, TipoLeito.SALA_VERMELHA, salasV);
    }

    private void inicializarLeitos(int qtd, TipoLeito tipo, List<Leito> lista) {
        for (int i = 1; i <= qtd; i++) {
            lista.add(new Leito((short) i, tipo, custoDiario));
        }
    }

    public Leito obterLeitoDisponivel(TipoLeito tipo) {
        List<Leito> lista = getListaPorTipo(tipo);
        for (Leito l : lista) {
            if (!l.isOcupado()) return l;
        }
        return null;
    }

    public void listarLeitosPorTipo(TipoLeito tipo) {
        List<Leito> lista = getListaPorTipo(tipo);
        for (Leito l : lista) {
            System.out.println(l);
        }
    }

    private List<Leito> getListaPorTipo(TipoLeito tipo) {
        return switch (tipo) {
            case AZUL_VERDE -> leitosAzulVerde;
            case AMARELO -> leitosAmarelo;
            case VERMELHO -> salasV;
        };
    }
}
