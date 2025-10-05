package PROCESSOS.INTERNACAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GerenciadorDeLeitos {
    !
    private final List<Leito> listaLeitos = new ArrayList<>();

    public GerenciadorDeLeitos() {
        for (int i = 1; i <= 10; i++) {
            listaLeitos.add(new Leito(i, TipoLeito.VERDE_AZUL));
        }
        // Ex: 5 leitos Amarelos (IDs de 11 a 15)
        for (int i = 11; i <= 15; i++) {
            listaLeitos.add(new Leito(i, TipoLeito.AMARELO));
        }
        // Ex: 3 leitos Vermelhos (IDs de 16 a 18)
        for (int i = 16; i <= 18; i++) {
            listaLeitos.add(new Leito(i, TipoLeito.VERMELHO));
        }
    }

    public Optional<Leito> buscarLeitoDesocupado(TipoLeito tipo) {
        for (Leito leito : listaLeitos) {
            if (leito.getTipo() == tipo && !leito.isOcupado()) {
                return Optional.of(leito); // Retorna o leito encontrado
            }
        }
        return Optional.empty();
    }

    public void listarTodosOsLeitos() {
        for (Leito leito : listaLeitos) {
            System.out.println(leito);
        }
    }
}