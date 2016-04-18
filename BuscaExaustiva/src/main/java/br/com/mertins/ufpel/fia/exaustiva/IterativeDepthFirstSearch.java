package br.com.mertins.ufpel.fia.exaustiva;

import br.com.mertins.ufpel.fia.util.BasicSearch;
import br.com.mertins.ufpel.fia.util.BoardState;
import br.com.mertins.ufpel.fia.util.Element;
import br.com.mertins.ufpel.fia.util.Observator;
import java.util.List;

/**
 *
 * @author mertins
 */
public class IterativeDepthFirstSearch extends BasicSearch {

    /**
     * Construtor para realizar busca em aprofundamento iterativo no
     * quebra-cabeça deslizante
     *
     * @param observator classe responsável em receber as informações que
     * servirão ao relatório
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     *
     */
    public IterativeDepthFirstSearch(Observator observator, boolean isShuffle) {
        super(observator, isShuffle);
    }

    @Override
    public List<BoardState> run() {
        int depth = 0;
        try {
            while (true) {
                BoardState testState = algDFS(beginState, depth);
                if (testState != null) {
                    observator.okSolution();
                    return this.makeSolution(testState);
                }
                depth++;
            }
        } catch (OutOfMemoryError ex) {
            observator.errSolution(depth);
            throw new RuntimeException(String.format("Memory full! Nivel atingido [%s]", depth));
        }
    }

    private BoardState algDFS(BoardState testState, int depth) {
        if (this.board.isTheSolution(testState)) {
            return testState;
        } else if (depth == 0) {
            return null;
        } else {
            Element[] findCandidates = this.board.findCandidates(testState, isShuffle);
            for (Element possibilidade : findCandidates) {
                BoardState move = this.board.move(possibilidade, testState);
                move.setHeight(testState.getHeight() + 1);
                move.setFather(testState);
//                this.board.print(testState);    // informações parciais
                BoardState ret = this.algDFS(move, depth - 1);
                if (ret != null && this.board.isTheSolution(ret)) {
                    return ret;
                }
            }
        }
        return null;
    }
}
