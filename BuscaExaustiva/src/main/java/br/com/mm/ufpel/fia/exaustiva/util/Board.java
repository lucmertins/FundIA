package br.com.mm.ufpel.fia.exaustiva.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mertins
 */
public class Board {

    private final BoardState solution;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.solution = this.build();
//        this.print(solution);
//        System.out.println();
    }

    public void print(BoardState boardState) {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.printf("%d\t", boardState.getSequence()[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public BoardState shuffle(int numVezes) {
        return this.shuffle(numVezes, this.solution);
    }

    public BoardState shuffle(int numVezes, BoardState boardState) {
        for (int i = 0; i < numVezes; i++) {
            Elemento[] candidatos = findCandidates(boardState);
            Random gerador = new Random();
            int escolhido = gerador.nextInt(candidatos.length);
            boardState= this.move(candidatos[escolhido], boardState);
        }
        return boardState;
    }

    public BoardState move(Elemento elemento, BoardState boardState) {
        BoardState newBoardState = new BoardState(boardState.getSequence());
        int[] posEsp = this.findSpace(newBoardState);
        newBoardState.getSequence()[posEsp[0]][posEsp[1]] = elemento.getValor();
        newBoardState.getSequence()[elemento.getX()][elemento.getY()] = 0;
        return newBoardState;
    }

    public Elemento[] findCandidates(BoardState boardState) {
        int[] posEsp = this.findSpace(boardState);
        List<Elemento> listCandidatos = new ArrayList<>();
        int x = posEsp[0] - 1;
        int y = posEsp[1];
        int[][] estado = boardState.getSequence();
        if (x > -1) {
            listCandidatos.add(new Elemento(x, y, estado[x][y]));
        }
        x = posEsp[0] + 1;
        if (x < this.size) {
            listCandidatos.add(new Elemento(x, y, estado[x][y]));
        }
        x = posEsp[0];
        y = posEsp[1] - 1;
        if (y > -1) {
            listCandidatos.add(new Elemento(x, y, estado[x][y]));
        }
        y = posEsp[1] + 1;
        if (y < this.size) {
            listCandidatos.add(new Elemento(x, y, estado[x][y]));
        }
        Elemento[] candidatos = new Elemento[listCandidatos.size()];
        candidatos = listCandidatos.toArray(candidatos);

        return candidatos;
    }

    public boolean isTheSolution(BoardState state) {
        int[][] solut = this.solution.getSequence();
        int[][] sit = state.getSequence();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (solut[i][j] != sit[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private BoardState build() {
        int[][] seqIni = new int[this.size][this.size];
        int peca = 1;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                seqIni[i][j] = peca++;
            }
        }
        seqIni[this.size - 1][this.size - 1] = 0;
        return new BoardState(seqIni);
    }

    private int[] findSpace(BoardState boardState) {
        int[] posEsp = new int[2];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (boardState.getSequence()[i][j] == 0) {
                    posEsp[1] = i;
                    posEsp[0] = j;
                    return posEsp;
                }
            }
        }
        return posEsp;
    }
}
