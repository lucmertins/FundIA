package br.com.mertins.ufpel.fia.exaustiva;

import br.com.mertins.ufpel.fia.util.BoardState;
import br.com.mertins.ufpel.fia.util.Event;
import br.com.mertins.ufpel.fia.util.Observator;
import java.util.List;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void depthFirstSearch(final Observator observador, final boolean isShuffle, final boolean showInitialState, boolean showSolution) {
        new Thread(() -> {
            DepthFirstSearch search = new DepthFirstSearch(observador, isShuffle);
            try {
                if (showInitialState) {
                    System.out.println("***************** Estado Inicial Depth First Search **********");
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

    public static void breadthFirstSearch(final Observator observador, final boolean isShuffle, final boolean showInitialState, boolean showSolution) {
        new Thread(() -> {
            BreadthFirstSearch search = new BreadthFirstSearch(observador, isShuffle);
            try {
                if (showInitialState) {
                    System.out.println("***************** Estado Inicial Breadth First Search **********");
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

    public static void iterativeDepthSearch(final Observator observador, final boolean isShuffle, final boolean showInitialState, boolean showSolution) {
        new Thread(() -> {
            IterativeDepthFirstSearch search = new IterativeDepthFirstSearch(observador, isShuffle);
            try {
                if (showInitialState) {
                    System.out.println("***************** Estado Inicial Iterative Depth First Search **********");
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

        Observator.ALGORITHMS opcao = Observator.ALGORITHMS.BFS;
        int tabuleiro = 3;
        int embaralhar = 50;
        boolean mostrarEstadoInicial = false;
        boolean mostrarSolucao = false;
        if (args.length == 5) {
            try {
                opcao = Observator.ALGORITHMS.valueOf(args[0]);
                tabuleiro = Integer.valueOf(args[1]);
                embaralhar = Integer.valueOf(args[2]);
                mostrarEstadoInicial = Boolean.valueOf(args[3]);
                mostrarSolucao = Boolean.valueOf(args[4]);
            } catch (Exception ex) {
                System.out.println("usar: DFS|BFS|IDS tamanhoTabuleiro vezesEmbaralhado mostrarEstadoInicial mostrarSolucao");
                System.out.println("Falha");
                ex.printStackTrace();
                System.exit(1);
            }
        }
        System.out.printf("Algoritmo[%s] Tamanho [%d] Embaralhado %d vezes\n ", opcao.toString(), tabuleiro, embaralhar);
        Observator observador = new Observator(opcao, tabuleiro, embaralhar);
        switch (opcao) {
            case DFS:
                Execute.depthFirstSearch(observador, true, mostrarEstadoInicial, mostrarSolucao);
                break;
            case BFS:
                Execute.breadthFirstSearch(observador, true, mostrarEstadoInicial, mostrarSolucao);
                break;
            case IDS:
                Execute.iterativeDepthSearch(observador, true, mostrarEstadoInicial, mostrarSolucao);
                break;

        }

    }
}
