package br.com.mertins.ufpel.fia.comheuristica;

import br.com.mertins.ufpel.fia.util.BoardState;
import br.com.mertins.ufpel.fia.util.Event;
import br.com.mertins.ufpel.fia.util.Observator;
import java.util.List;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void aStarSearch(final Observator observador, final boolean isShuffle, final boolean showInitialState, final boolean showSolution, AStarSearch.Heuristics heuristic) {
        new Thread(() -> {
            AStarSearch search = new AStarSearch(observador, isShuffle, heuristic);
            try {
                if (showInitialState) {
                    System.out.println("***************** Estado Inicial A* Search **********");
                    search.print(search.getBeginState());
                }
                List<BoardState> solucao = search.run();
                if (showSolution) {
                    System.out.println("***************** Solução **********");
                    search.print(solucao);
                }
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

        Observator.ALGORITHMS opcao = Observator.ALGORITHMS.ASTAR;
        int tabuleiro = 4;
        int embaralhar = 500;
        int tempoEmMinutos = 5;
        AStarSearch.Heuristics heuristic = AStarSearch.Heuristics.MANHATAN;
        boolean mostrarEstadoInicial = false;
        boolean mostrarSolucao = false;
        if (args.length == 7) {
            try {
                opcao = Observator.ALGORITHMS.valueOf(args[0]);
                tabuleiro = Integer.valueOf(args[1]);
                embaralhar = Integer.valueOf(args[2]);
                tempoEmMinutos = Integer.valueOf(args[3]);
                mostrarEstadoInicial = Boolean.valueOf(args[4]);
                mostrarSolucao = Boolean.valueOf(args[5]);
                heuristic = AStarSearch.Heuristics.valueOf(args[6]);
            } catch (Exception ex) {
                System.out.println("usar: ASTAR tamanhoTabuleiro vezesEmbaralhado tempoEmMinutos mostrarEstadoInicial mostrarSolucao heuristica");
                System.out.println("Falha");
                ex.printStackTrace();
                System.exit(1);
            }
        }
        System.out.printf("Algoritmo[%s] Tamanho [%d] Heuristica [%s] Embaralhado %d vezes \n ", opcao.toString(), tabuleiro, heuristic, embaralhar);
        Observator observador = new Observator(opcao, tabuleiro, embaralhar, tempoEmMinutos);
        switch (opcao) {
            case ASTAR:
                Execute.aStarSearch(observador, true, mostrarEstadoInicial, mostrarSolucao, heuristic);
                break;

        }

    }
}
