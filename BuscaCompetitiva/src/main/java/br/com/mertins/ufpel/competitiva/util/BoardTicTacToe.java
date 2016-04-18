package br.com.mertins.ufpel.competitiva.util;

import br.com.mertins.ufpel.fia.util.BoardState;
import java.util.List;

/**
 *
 * @author mertins
 */
public class BoardTicTacToe {

    private int[][] tabuleiro = new int[3][3];

    public BoardTicTacToe() {
    }

    public void print() {
        for (int y = 0; y < tabuleiro.length; y++) {
            for (int x = 0; x < tabuleiro.length; x++) {
                System.out.printf("%d\t", tabuleiro[y][x]);
            }
            System.out.println();
        }
    }
}
