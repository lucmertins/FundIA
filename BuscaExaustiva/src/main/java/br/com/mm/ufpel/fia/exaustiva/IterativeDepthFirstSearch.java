package br.com.mm.ufpel.fia.exaustiva;

/**
 *
 * @author mertins
 */
public class IterativeDepthFirstSearch extends DepthFirstSearch {

    /**
     * Construtor para realizar busca em aprofundamento iterativo no quebra-cabeça
     * deslizante
     *
     * @param size tamanho do tabuleiro
     * @param shuffle quantidade de embaralhamento das peças
     * @param collectionLimit limite máximo de elementos na collection
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     *
     */
    public IterativeDepthFirstSearch(int size, int shuffle, int collectionLimit, boolean isShuffle) {
        super(size, shuffle, collectionLimit, isShuffle);
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