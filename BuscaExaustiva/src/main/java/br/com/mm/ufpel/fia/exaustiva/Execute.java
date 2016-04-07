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

    public static void iterativeDepthSearch(final int size, final int shuffle, final boolean isShuffle) {
        new Thread(() -> {
            IterativeDepthFirstSearch search = new IterativeDepthFirstSearch(size, shuffle, isShuffle);
            try {
                System.out.println("***************** Estado Inicial Iterative Depth First Search **********");
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
        DFS, BFS, IDS
    }

    public static void main(String[] args) {
        int processadores = Runtime.getRuntime().availableProcessors();
        long totalMemoria = Runtime.getRuntime().totalMemory() / 1048576;
        long livreMemoria = Runtime.getRuntime().freeMemory() / 1048576;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1048576;
        System.out.printf("\nProcessadores [%d]       Memória disponível [%dM]      Livre [%dM] Máx[%dM]\n\n", processadores, totalMemoria, livreMemoria, maxMemory);
        ALGORITHMS opcao = ALGORITHMS.DFS;
        int tabuleiro = 3;
        int embaralhar = 40;
        if (args.length == 3) {
            try {
                opcao = ALGORITHMS.valueOf(args[0]);
                tabuleiro = Integer.valueOf(args[1]);
                embaralhar = Integer.valueOf(args[2]);
            } catch (Exception ex) {
                System.out.println("usar: DFS|BFS|IDS tamanhoTabuleiro vezesEmbaralhado ");
                System.out.println("Falha");
                ex.printStackTrace();
                System.exit(1);
            }
        } else {
            System.out.println("usando o padrão : IDS 3 40 ");
        }
        System.out.println();
        switch (opcao) {
            case DFS:
                Execute.depthFirstSearch(tabuleiro, embaralhar, true);
                break;
            case BFS:
                Execute.breadthFirstSearch(tabuleiro, embaralhar, true);
                break;
            case IDS:
                Execute.iterativeDepthSearch(tabuleiro, embaralhar, true);
                break;
        }

    }
}
