package br.com.mm.ufpel.fia.exaustiva;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    DepthFirstSearch search = new DepthFirstSearch(3, 20, 100000);
//                    search.run();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BreadthFirstSearch search = new BreadthFirstSearch(3, 40, 10000000);
                    search.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
