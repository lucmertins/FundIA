package br.com.mertins.ufpel.fia.util;

import java.util.ArrayList;
import java.util.Collections;
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
    }

    public void print(List<BoardState> lista) {
        System.out.println("------------------------------------");
        for (BoardState estado : lista) {
            this.print(estado);
        }
        System.out.println("------------------------------------");
    }

    public void print(BoardState boardState) {
        System.out.printf("Altura %s      Heuristica %d\n", boardState.getHeight(), boardState.getValueHeuristic());
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                System.out.printf("%d\t", boardState.getSequence()[y][x]);
            }
            System.out.println();
        }
    }

    public BoardState shuffle(int numVezes) {
        return this.shuffle(numVezes, this.solution);
    }

    public BoardState shuffle(int numVezes, BoardState boardState) {
        for (int i = 0; i < numVezes; i++) {
            Element[] candidatos = findCandidates(boardState, false);
            Random gerador = new Random();
            int escolhido = gerador.nextInt(candidatos.length);
            boardState = this.move(candidatos[escolhido], boardState);
        }
        return boardState;
    }

    public BoardState move(Element elemento, BoardState boardState) {
        BoardState newBoardState = new BoardState(boardState.getSequence());
        int[] posEsp = this.findSpace(newBoardState);
        newBoardState.getSequence()[posEsp[0]][posEsp[1]] = elemento.getValue();
        newBoardState.getSequence()[elemento.getY()][elemento.getX()] = 0;
        return newBoardState;
    }

    public Element[] findCandidates(BoardState boardState, boolean isShuffle) {
        int[] posEsp = this.findSpace(boardState);
        List<Element> listCandidatos = new ArrayList<>();
        int y = posEsp[0];
        int x = posEsp[1] - 1;
        int[][] estado = boardState.getSequence();
        if (x > -1) {
            listCandidatos.add(new Element(y, x, estado[y][x]));
        }
        x = posEsp[1] + 1;
        if (x < this.size) {
            listCandidatos.add(new Element(y, x, estado[y][x]));
        }
        x = posEsp[1];
        y = posEsp[0] - 1;
        if (y > -1) {
            listCandidatos.add(new Element(y, x, estado[y][x]));
        }
        y = posEsp[0] + 1;
        if (y < this.size) {
            listCandidatos.add(new Element(y, x, estado[y][x]));
        }
        if (isShuffle) {
            Collections.shuffle(listCandidatos);
        }
        Element[] candidatos = new Element[listCandidatos.size()];
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
