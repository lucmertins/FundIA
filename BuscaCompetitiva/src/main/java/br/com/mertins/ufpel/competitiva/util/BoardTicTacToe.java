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
        byte[][] situacao = this.buildBoard(node);
        boolean espacoLivre=false;
        for (int y = 0; y < tabuleiro.length; y++) {
            for (int x = 0; x < tabuleiro.length; x++) {
                if ((tabuleiro[y][0].getMarker() == tabuleiro[y][1].getMarker() && tabuleiro[y][1].getMarker() == tabuleiro[y][2].getMarker())
                        || (tabuleiro[0][x].getMarker() == tabuleiro[1][x].getMarker() && tabuleiro[1][x].getMarker() == tabuleiro[2][x].getMarker())
                        || (tabuleiro[0][0].getMarker() == tabuleiro[1][1].getMarker() && tabuleiro[1][1].getMarker() == tabuleiro[2][2].getMarker())
                        || (tabuleiro[2][0].getMarker() == tabuleiro[1][1].getMarker() && tabuleiro[1][1].getMarker() == tabuleiro[0][2].getMarker())) {
                    return true;
                }
                if (tabuleiro[y][x].getMarker() == Node.Marker.B) {
                    espacoLivre=true;
                }
            }
        }
        return !espacoLivre;
    }

    public Node heuristic(Node node) {
        return null;  // avaliar como entregar valores adequados
    }

    public boolean gameOver(Node node) {
        boolean espacoLivre=false;
        for (int y = 0; y < tabuleiro.length; y++) {
            for (int x = 0; x < tabuleiro.length; x++) {
                if ((tabuleiro[y][0].getMarker() == tabuleiro[y][1].getMarker() && tabuleiro[y][1].getMarker() == tabuleiro[y][2].getMarker() && tabuleiro[y][2].getMarker()==Node.Marker.X)
                        || (tabuleiro[0][x].getMarker() == tabuleiro[1][x].getMarker() && tabuleiro[1][x].getMarker() == tabuleiro[2][x].getMarker()&&tabuleiro[2][x].getMarker()==Node.Marker.X)
                        || (tabuleiro[0][0].getMarker() == tabuleiro[1][1].getMarker() && tabuleiro[1][1].getMarker() == tabuleiro[2][2].getMarker()&&tabuleiro[2][2].getMarker()==Node.Marker.X)
                        || (tabuleiro[2][0].getMarker() == tabuleiro[1][1].getMarker() && tabuleiro[1][1].getMarker() == tabuleiro[0][2].getMarker()&&tabuleiro[0][2].getMarker()==Node.Marker.X)) {
                    return true;  //ganhou 
                }
                if ((tabuleiro[y][0].getMarker() == tabuleiro[y][1].getMarker() && tabuleiro[y][1].getMarker() == tabuleiro[y][2].getMarker() && tabuleiro[y][2].getMarker()==Node.Marker.O)
                        || (tabuleiro[0][x].getMarker() == tabuleiro[1][x].getMarker() && tabuleiro[1][x].getMarker() == tabuleiro[2][x].getMarker()&&tabuleiro[2][x].getMarker()==Node.Marker.O)
                        || (tabuleiro[0][0].getMarker() == tabuleiro[1][1].getMarker() && tabuleiro[1][1].getMarker() == tabuleiro[2][2].getMarker()&&tabuleiro[2][2].getMarker()==Node.Marker.O)
                        || (tabuleiro[2][0].getMarker() == tabuleiro[1][1].getMarker() && tabuleiro[1][1].getMarker() == tabuleiro[0][2].getMarker()&&tabuleiro[0][2].getMarker()==Node.Marker.O)) {
                    return true;  //perdeu
                }
                if (tabuleiro[y][x].getMarker() == Node.Marker.B) {
                    return espacoLivre=true;
                }
            }
        }
        return !espacoLivre;   //se não tem espaco livre e empate 
    }

    private void clear() {
        for (Node[] tabuleiro1 : tabuleiro) {
            for (int x = 0; x < tabuleiro.length; x++) {
                tabuleiro1[x] = new Node(Node.Infinite.POSITIVE, Node.Marker.B);
            }
        }
    }

    private byte[][] buildBoard(Node node) {
        byte[][] board = new byte[3][3];
        if (node.getMarker() != Node.Marker.B) {
            board[node.getPosY()][node.getPosX()] = (byte) node.getMarker().toByte();
        }
        Node temp = node.getParent();
        while (temp != null) {
            board[temp.getPosY()][temp.getPosX()] = (byte) temp.getMarker().toByte();
            temp = node.getParent();
        }
        return board;
    }
}
