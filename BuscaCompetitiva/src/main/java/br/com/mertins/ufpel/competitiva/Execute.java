package br.com.mertins.ufpel.competitiva;

import br.com.mertins.ufpel.fia.util.BoardState;
import br.com.mertins.ufpel.fia.util.Event;
import br.com.mertins.ufpel.fia.util.Observator;
import java.util.List;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void miniMax(final Observator observador) {
        new Thread(() -> {
            MiniMax search = new MiniMax(observador);
            try {
                List<BoardState> solucao = search.run();
                System.out.println("***************** Solução **********");
                search.print(solucao);
                System.out.printf("***************** Movimentos [%d]\n", solucao.size());
            } catch (Exception ex) {
                System.out.println("***************** Falha **********");
                ex.printStackTrace();
            }
            for (Event evento : observador.getEvents()) {
                System.out.println(evento.toString());
            }
            System.out.printf("Tempo de execução [%s]\n", search.time(observador.difference()));

        }).start();
    }

    public static void main(String[] args) {

        Observator.ALGORITHMS opcao = Observator.ALGORITHMS.MINIMAX;
        System.out.printf("Algoritmo[%s] \n ", opcao.toString());
        Observator observador = new Observator(opcao, -1, -1);
        switch (opcao) {
            case MINIMAX:
                Execute.miniMax(observador);
                break;

        }

    }
}
