package br.com.mertins.ufpel.competitiva.util;

/**
 *
 * @author mertins
 */
public class BoardTicTacToe {

    private final Node[][] tabuleiro = new Node[3][3];
    private final Node nodeBegin = new Node(Node.Infinite.POSITIVE, Node.Marker.X);

    public BoardTicTacToe() {
        this.clear();
    }

    public Node getNodeBegin() {
        return nodeBegin;
    }

    public Node[] findCandidates(Node node) {
        return null;
    }

    public void print() {
        System.out.println("");
        for (int y = 0; y < tabuleiro.length; y++) {
            for (int x = 0; x < tabuleiro.length; x++) {
                System.out.printf("%s\t", tabuleiro[y][x].getMarker().toString());
            }
            System.out.println();
        }
    }

    public boolean terminal(Node node) {
        return false;
    }

    public Node heuristic(Node node) {
        return null;  // avaliar como entregar valores adequados
    }

    public boolean gameOver(Node node) {
        return false; //avaliar se impate, ganhou player, ganho adversario
    }

    private void clear() {
        for (Node[] tabuleiro1 : tabuleiro) {
            for (int x = 0; x < tabuleiro.length; x++) {
                tabuleiro1[x] = new Node(Node.Infinite.POSITIVE, Node.Marker.B);
            }
        }
    }
}
