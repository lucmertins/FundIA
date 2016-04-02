package br.com.mm.ufpel.fia.exaustiva;

import java.io.Serializable;

/**
 *
 * @author mertins
 */
public class BoardState implements Serializable {

    private final int[][] sequence;

    public BoardState(int[][] sequence) {
        this.sequence = sequence;
    }

    public int[][] getSequence() {
        return sequence;
    }
}
