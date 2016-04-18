package br.com.mm.ufpel.fia;

import br.com.mm.ufpel.fia.exaustiva.BreadthFirstSearch;
import br.com.mm.ufpel.fia.exaustiva.DepthFirstSearch;
import br.com.mm.ufpel.fia.exaustiva.IterativeDepthFirstSearch;
import br.com.mm.ufpel.fia.util.BoardState;
import br.com.mm.ufpel.fia.util.Event;
import br.com.mm.ufpel.fia.util.Observator;
import java.util.List;

/**
 *
 * @author mertins
 */
public class ExecuteExaustiva {

    public static void depthFirstSearch(final Observator observador, final boolean isShuffle) {
        new Thread(() -> {
            DepthFirstSearch search = new DepthFirstSearch(observador, isShuffle);
            try {
                System.out.println("***************** Estado Inicial Depth First Search **********");
                search.print(search.getBeginState());
                List<BoardState> solucao = search.run();
//                System.out.println("***************** Solução **********");
//                search.print(solucao);
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

    public static void breadthFirstSearch(final Observator observador, final boolean isShuffle) {
        new Thread(() -> {
            BreadthFirstSearch search = new BreadthFirstSearch(observador, isShuffle);
            try {
                System.out.println("***************** Estado Inicial Breadth First Search **********");
                search.print(search.getBeginState());
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

    public static void iterativeDepthSearch(final Observator observador, final boolean isShuffle) {
        new Thread(() -> {
            IterativeDepthFirstSearch search = new IterativeDepthFirstSearch(observador, isShuffle);
            try {
                System.out.println("***************** Estado Inicial Iterative Depth First Search **********");
                search.print(search.getBeginState());
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

        Observator.ALGORITHMS opcao = Observator.ALGORITHMS.ASTAR;
        int tabuleiro = 3;
        int embaralhar = 100;
        if (args.length == 3) {
            try {
                opcao = Observator.ALGORITHMS.valueOf(args[0]);
                tabuleiro = Integer.valueOf(args[1]);
                embaralhar = Integer.valueOf(args[2]);
            } catch (Exception ex) {
                System.out.println("usar: DFS|BFS|IDS tamanhoTabuleiro vezesEmbaralhado");
                System.out.println("Falha");
                ex.printStackTrace();
                System.exit(1);
            }
        }
        System.out.printf("Algoritmo[%s] Tamanho [%d] Embaralhado %d vezes\n ", opcao.toString(), tabuleiro, embaralhar);
        Observator observador = new Observator(opcao, tabuleiro, embaralhar);
        switch (opcao) {
            case DFS:
                ExecuteExaustiva.depthFirstSearch(observador, true);
                break;
            case BFS:
                ExecuteExaustiva.breadthFirstSearch(observador, true);
                break;
            case IDS:
                ExecuteExaustiva.iterativeDepthSearch(observador, true);
                break;

        }

    }
}
