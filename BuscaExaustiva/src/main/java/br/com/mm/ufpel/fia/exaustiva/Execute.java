package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import java.util.List;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void depthFirstSearch(final int size, final int shuffle, final int collectionLimit, final boolean isShuffle) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DepthFirstSearch search = new DepthFirstSearch(size, shuffle, collectionLimit, isShuffle);
                    search.printTime();
                    List<BoardState> solucao = search.run();
                    search.printTime();
                    System.out.println("***************** Estado Inicial **********");
                    search.print(search.getBeginState());
                    System.out.println("***************** Solução **********");
                    search.print(solucao);
                    System.out.printf("***************** Movimentos [%d]\n", solucao.size());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void breadthFirstSearch(final int size, final int shuffle, final int collectionLimit, final boolean isShuffle) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BreadthFirstSearch search = new BreadthFirstSearch(size, shuffle, collectionLimit, isShuffle);
                    search.printTime();
                    List<BoardState> solucao = search.run();
                    search.printTime();
                    System.out.println("***************** Estado Inicial **********");
                    search.print(search.getBeginState());
                    System.out.println("***************** Solução **********");
                    search.print(solucao);
                    System.out.printf("***************** Movimentos [%d]\n", solucao.size());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
//        Execute.depthFirstSearch(3, 40, 10000000, true);
        Execute.breadthFirstSearch(3, 40, 10000000, true);

    }
}
