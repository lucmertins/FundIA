package br.com.mertins.ufpel.fia.competitiva.util;

/**
 *
 * @author mertins
 */
public class BoardTicTacToeState {

    private final Node[][] state;
    private final int height;

    public BoardTicTacToeState(Node[][] state, int height) {
        this.state = state;
        this.height = height;
    }

    public Node[][] getState() {
        return state;
    }

    public int getHeight() {
        return height;
    }

}
