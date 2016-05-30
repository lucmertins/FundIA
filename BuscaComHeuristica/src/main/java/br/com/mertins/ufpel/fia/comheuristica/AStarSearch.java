package br.com.mertins.ufpel.fia.comheuristica;

import br.com.mertins.ufpel.fia.util.BasicSearch;
import br.com.mertins.ufpel.fia.util.BoardState;
import br.com.mertins.ufpel.fia.util.Element;
import br.com.mertins.ufpel.fia.util.Observator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author mertins
 */
public class AStarSearch extends BasicSearch {

    public enum Heuristics {
        MANHATAN, HAMMING
    }

    private final Heuristics heuristic;

    /**
     * Construtor para realizar busca A* no quebra-cabeça deslizante
     *
     * @param observator classe responsável em receber as informações que
     * servirão ao relatório
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     * @param withHash usa tabela de hash para evitar movimentos já avaliados
     * @param heuristic qual função heuristica deve ser utilizada
     */
    public AStarSearch(Observator observator, boolean isShuffle, boolean withHash, Heuristics heuristic) {
        super(observator, isShuffle, withHash);
        this.heuristic = heuristic;
    }

    @Override
    public List<BoardState> run() {
        Queue<BoardState> lista = new PriorityQueue<>();
        lista.add(beginState);
        int nivel = 0;
        long hashColision = 0;
        try {
            while (!lista.isEmpty()) {
                if (observator.isTimeOver()) {
                    observator.errSolution(nivel);
                    throw new RuntimeException(String.format("Tempo excedido! Nivel atingido [%s]", nivel));
                }
                BoardState testState = lista.poll();
                testState.setValueHeuristic(heuristic(testState, nivel));
//                this.board.print(testState);    // informações parciais
                if (!this.board.isTheSolution(testState)) {
                    nivel = testState.getHeight() + 1;
                    Element[] findCandidates = this.board.findCandidates(testState, isShuffle);
                    for (Element possibilidade : findCandidates) {
                        BoardState move = this.board.move(possibilidade, testState);
                        if (!withHash || !this.hashTable.contains(move)) {
                            move.setHeight(nivel);
                            move.setFather(testState);
                            move.setValueHeuristic(heuristic(move, nivel));
                            if (withHash) {
                                this.hashTable.add(move);
                            }
                            lista.add(move);
                        } else {
                            observator.setHashColision(hashColision++);
                        }
                    }
                    observator.setChangePath(nivel);
                } else {
                    observator.okSolution();
                    lista.clear();
                    return this.makeSolution(testState);
                }
            }
        } catch (OutOfMemoryError ex) {
            observator.errSolution(nivel);
            lista.clear();
            throw new RuntimeException(String.format("Memory full! Nivel atingido [%s]", nivel));
        }
        observator.errSolution(nivel);
        throw new RuntimeException("Falha! Não encontrou solução e Collection vazia");
    }

    private int heuristic(BoardState state, int nivel) {
        int value = -1;
        switch (this.heuristic) {
            case MANHATAN:
                value = this.heuristicaManhatan(state, nivel);
                break;
            case HAMMING:
                value = this.heuristicaHamming(state, nivel);
                break;
        }
        return value;
    }

    private int heuristicaManhatan(BoardState state, int nivel) {
        int[][] sit = state.getSequence();
        int size = sit.length;
        int acumul = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int value = sit[y][x];
                if (value > 0) {
                    int yCerto = (value - 1) / size;
                    int xCerto = (value - 1) % size;
                    int xManhatan = Math.abs(xCerto - x);
                    int yManhatan = Math.abs(yCerto - y);
                    acumul += xManhatan + yManhatan;
                }
            }
        }
        return acumul + nivel;
    }

    private int heuristicaHamming(BoardState state, int nivel) {
        int[][] sit = state.getSequence();
        int size = sit.length;
        int peca = 1;
        int acumul = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (peca != sit[y][x]) {
                    acumul++;
                }
            }
        }
        return acumul + nivel;
    }
}
