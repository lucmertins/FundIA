package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import java.util.List;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void depthFirstSearch(final int size, final int shuffle, final boolean isShuffle) {
        new Thread(() -> {
            DepthFirstSearch search = new DepthFirstSearch(size, shuffle, isShuffle);
            try {
                System.out.println("***************** Estado Inicial Depth First Search **********");
                search.print(search.getBeginState());
                search.printTime();
                List<BoardState> solucao = search.run();
                search.printTime();
                System.out.println("***************** Solução **********");
                search.print(solucao);
                System.out.printf("***************** Movimentos [%d]\n", solucao.size());
            } catch (Exception ex) {
                System.out.println("***************** Falha **********");
                search.printTime();
                ex.printStackTrace();
            }
        }).start();
    }

    public static void breadthFirstSearch(final int size, final int shuffle, final boolean isShuffle) {
        new Thread(() -> {
            BreadthFirstSearch search = new BreadthFirstSearch(size, shuffle, isShuffle);
            try {
                System.out.println("***************** Estado Inicial Breadth First Search **********");
                search.print(search.getBeginState());
                search.printTime();
                List<BoardState> solucao = search.run();
                search.printTime();
                System.out.println("***************** Solução **********");
                search.print(solucao);
                System.out.printf("***************** Movimentos [%d]\n", solucao.size());
            } catch (Exception ex) {
                System.out.println("***************** Falha **********");
                search.printTime();
                ex.printStackTrace();
            }
        }).start();
    }

    private enum ALGORITHMS {
        DFS, BFS
    }

    public static void main(String[] args) {
        int processadores = Runtime.getRuntime().availableProcessors();
        long totalMemoria = Runtime.getRuntime().totalMemory() / 1048576;
        long livreMemoria = Runtime.getRuntime().freeMemory() / 1048576;
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.printf("\nProcessadores [%d]       Memória disponível [%dM]      Livre [%dM] Máx[%dM]\n\n", processadores, totalMemoria, livreMemoria, maxMemory);
        ALGORITHMS opcao = ALGORITHMS.BFS;
        if (args.length == 1) {
            try {
                opcao = ALGORITHMS.valueOf(args[0]);
            } catch (Exception ex) {
            }
        }
        switch (opcao) {
            case DFS:
                Execute.depthFirstSearch(3, 40, true);
                break;
            case BFS:
                Execute.breadthFirstSearch(3, 40, true);
                break;
        }
    }
}
