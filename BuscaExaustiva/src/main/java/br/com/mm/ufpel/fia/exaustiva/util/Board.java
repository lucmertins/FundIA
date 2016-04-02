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
        System.out.printf("Nível %s\n",boardState.getHeight());
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                System.out.printf("%d\t", boardState.getSequence()[y][x]);
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
            boardState = this.move(candidatos[escolhido], boardState);
        }
        return boardState;
    }

    public BoardState move(Elemento elemento, BoardState boardState) {
        BoardState newBoardState = new BoardState(boardState.getSequence());
        int[] posEsp = this.findSpace(newBoardState);
        newBoardState.getSequence()[posEsp[0]][posEsp[1]] = elemento.getValor();
        newBoardState.getSequence()[elemento.getY()][elemento.getX()] = 0;
        return newBoardState;
    }

    public Elemento[] findCandidates(BoardState boardState) {
        int[] posEsp = this.findSpace(boardState);
        List<Elemento> listCandidatos = new ArrayList<>();
        int y = posEsp[0];
        int x = posEsp[1] - 1;
        int[][] estado = boardState.getSequence();
        if (x > -1) {
            listCandidatos.add(new Elemento(y, x, estado[y][x]));
        }
        x = posEsp[1] + 1;
        if (x < this.size) {
            listCandidatos.add(new Elemento(y, x, estado[y][x]));
        }
        x = posEsp[1];
        y = posEsp[0] - 1;
        if (y > -1) {
            listCandidatos.add(new Elemento(y, x, estado[y][x]));
        }
        y = posEsp[0] + 1;
        if (y < this.size) {
            listCandidatos.add(new Elemento(y, x, estado[y][x]));
        }
        Elemento[] candidatos = new Elemento[listCandidatos.size()];
        candidatos = listCandidatos.toArray(candidatos);

        return candidatos;
    }

    public boolean isTheSolution(BoardState state) {
        int[][] solut = this.solution.getSequence();
        int[][] sit = state.getSequence();
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                if (solut[y][x] != sit[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    private BoardState build() {
        int[][] seqIni = new int[this.size][this.size];
        int peca = 1;
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                seqIni[y][x] = peca++;
            }
        }
        seqIni[this.size - 1][this.size - 1] = 0;
        return new BoardState(seqIni);
    }

    private int[] findSpace(BoardState boardState) {
        int[] posEsp = new int[2];
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                if (boardState.getSequence()[y][x] == 0) {
                    posEsp[0] = y;
                    posEsp[1] = x;
                    return posEsp;
                }
            }
        }
        return posEsp;
    }
}
