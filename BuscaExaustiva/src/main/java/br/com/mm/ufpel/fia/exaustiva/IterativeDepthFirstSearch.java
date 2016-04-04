package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.BasicSearch;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Element;
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
     * @param size tamanho do tabuleiro
     * @param shuffle quantidade de embaralhamento das peças
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     *
     */
    public IterativeDepthFirstSearch(int size, int shuffle, boolean isShuffle) {
        super(size, shuffle, isShuffle);
    }

    @Override
    public List<BoardState> run() {
        int depth = 0;
        try {
            while (true) {
                BoardState testState = algDFS(beginState, depth);
                if (testState != null) {
                    return this.makeSolution(testState);
                }
                depth++;
            }
        } catch (OutOfMemoryError ex) {
            long totalMemoria = Runtime.getRuntime().totalMemory() / 1048576;
            long livreMemoria = Runtime.getRuntime().freeMemory() / 1048576;
            throw new RuntimeException(String.format("Memory full! Nivel atingido [%s]       Memória disponível [%dM]      Livre [%dM]", depth, totalMemoria, livreMemoria));
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
