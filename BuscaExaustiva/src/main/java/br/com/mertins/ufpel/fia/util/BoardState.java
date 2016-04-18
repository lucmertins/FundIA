package br.com.mertins.ufpel.fia.util;

import java.io.Serializable;

/**
 *
 * @author mertins
 */
public class BoardState implements Serializable, Comparable {

    private final int[][] sequence;
    private int height;
    private BoardState father;
    private int valueHeuristic;

    public BoardState(int[][] sequence) {
        this.sequence = new int[sequence.length][sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            System.arraycopy(sequence[i], 0, this.sequence[i], 0, sequence.length);
        }
    }

    public int[][] getSequence() {
        return sequence;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BoardState getFather() {
        return father;
    }

    public void setFather(BoardState father) {
        this.father = father;
    }

    public int getValueHeuristic() {
        return valueHeuristic;
    }

    public void setValueHeuristic(int valueHeuristic) {
        this.valueHeuristic = valueHeuristic;
    }

    @Override
    public int compareTo(Object o) {
        return this.valueHeuristic - ((BoardState) o).valueHeuristic;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.valueHeuristic;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardState other = (BoardState) obj;
        if (this.valueHeuristic != other.valueHeuristic) {
            return false;
        }
        return true;
    }

}
