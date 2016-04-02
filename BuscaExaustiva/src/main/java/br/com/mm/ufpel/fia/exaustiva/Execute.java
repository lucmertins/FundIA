package br.com.mm.ufpel.fia.exaustiva;

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
                    DepthFirstSearch search = new DepthFirstSearch(3, 20, 10000000, true);
                    search.run();
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
                    BreadthFirstSearch search = new BreadthFirstSearch(3, 40, 10000000, false);
                    search.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        Execute.depthFirstSearch();
//        Execute.breadthFirstSearch();

    }
}
