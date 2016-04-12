package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.BasicSearch;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Element;
import br.com.mm.ufpel.fia.exaustiva.util.Observator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author mertins
 */
public class AStarSearch extends BasicSearch {

    /**
     * Construtor para realizar busca A* no quebra-cabeça deslizante
     *
     * @param observator classe responsável em receber as informações que
     * servirão ao relatório
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     */
    public AStarSearch(Observator observator, boolean isShuffle) {
        super(observator, isShuffle);
    }

    @Override
    public List<BoardState> run() {
        Queue<BoardState> lista = new LinkedList<>();
        lista.add(beginState);
        int nivel = 0;
        try {
            while (!lista.isEmpty()) {
                BoardState testState = lista.poll();
                this.board.print(testState);    // informações parciais
                if (!this.board.isTheSolution(testState)) {
                    nivel = testState.getHeight() + 1;
                    Element[] findCandidates = this.board.findCandidates(testState, isShuffle);
                    for (Element possibilidade : findCandidates) {
                        BoardState move = this.board.move(possibilidade, testState);
                        move.setHeight(nivel);
                        move.setFather(testState);
                        move.setValueHeuristic(heuristica(move));
                        lista.add(move);
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

    private int heuristica(BoardState state) {
        int[][] sit = state.getSequence();
        int size = sit.length;
        int acumul = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int value = sit[y][x];
                int yCerto = (value - 1) / size;
                int xCerto = (value - 1) % size;
                int xManhatan = Math.abs(xCerto - x);
                int yManhatan = Math.abs(yCerto - y);
                acumul += xManhatan + yManhatan;
            }
        }
        return acumul;
    }
}
