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
    private int hash=0;

    public BoardState(int[][] sequence) {
        this.sequence = new int[sequence.length][sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            System.arraycopy(sequence[i], 0, this.sequence[i], 0, sequence.length);
        }
        hash=this.hashCode();
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

    public int getHash() {
        return hash;
    }

    @Override
    public int hashCode() {
        int base = 0;
        int hash = 0;
        int size = this.sequence.length;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                hash +=  Math.pow(10, base)*sequence[y][x];
                base++;
            }
        }
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
        int size = this.sequence.length;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (sequence[y][x] != other.sequence[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

}
