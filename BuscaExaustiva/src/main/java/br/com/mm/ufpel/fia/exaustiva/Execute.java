package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.Board;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import java.util.List;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void depthFirstSearch() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DepthFirstSearch search = new DepthFirstSearch(3, 40, 10000000, true);
                    List<BoardState> solucao = search.run();
                    Board quebracabeca = new Board(3);
                    System.out.println("***************** Solução **********");
                    quebracabeca.print(solucao);
                    System.out.printf("***************** Movimentos [%d]\n", solucao.size());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void breadthFirstSearch() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BreadthFirstSearch search = new BreadthFirstSearch(3, 3, 10000000, false);
                    List<BoardState> solucao = search.run();
                    Board quebracabeca = new Board(3);
                    System.out.println("***************** Solução **********");
                    quebracabeca.print(solucao);
                    System.out.printf("***************** Movimentos [%d]\n", solucao.size());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
//        Execute.depthFirstSearch();
        Execute.breadthFirstSearch();

    }
}
