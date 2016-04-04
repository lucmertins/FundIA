package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.BasicSearch;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Element;
import java.util.List;
import java.util.Stack;

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
        int nivel = 0;
        try {
            while (true) {
                BoardState testState = algDFS(beginState, nivel);
//                this.board.print(testState);   // informações parciais
                if (testState != null) {
                    return this.makeSolution(testState);
                }
                nivel++;
            }
        } catch (OutOfMemoryError ex) {
            long totalMemoria = Runtime.getRuntime().totalMemory() / 1048576;
            long livreMemoria = Runtime.getRuntime().freeMemory() / 1048576;
            throw new RuntimeException(String.format("Memory full! Nivel atingido [%s]       Memória disponível [%dM]      Livre [%dM]", nivel, totalMemoria, livreMemoria));
        }
    }

    private BoardState algDFS(BoardState testState, int depth) {
        if (!this.board.isTheSolution(testState)) {
            return testState;
        } else if (depth == 0) {
            return null;
        } else {
            Element[] findCandidates = this.board.findCandidates(testState, isShuffle);
            for (Element possibilidade : findCandidates) {
                BoardState move = this.board.move(possibilidade, testState);
                BoardState ret = this.algDFS(move, depth - 1);
                if (this.board.isTheSolution(ret)) {
                    return ret;
                }
            }
        }
        return null;
    }
}
//
//https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
//
//procedure IDDFS(root)
//    for depth from 0 to ∞
//        found ← DLS(root, depth)
//        if found ≠ null
//            return found
//
//procedure DLS(node, depth)
//    if depth = 0 and node is a goal
//        return node
//    else if depth > 0
//        foreach child of node
//            found ← DLS(child, depth−1)
//            if found ≠ null
//                return found
//    return null
