package br.com.mm.ufpel.fia.exaustiva;

/**
 * Busca em profundidade
 *
 * @author mertins
 */
public class DepthFirstSearch {

    private final BoardState beginState;

    public DepthFirstSearch(int size, int shuffle) {
        Board tabuleiro = new Board(size);
        beginState = tabuleiro.shuffle(shuffle);
        tabuleiro.print(this.beginState);

    }

    public static void main(String[] args) {
        DepthFirstSearch search = new DepthFirstSearch(3, 1000);
    }
}
