package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.Board;
import br.com.mm.ufpel.fia.exaustiva.util.BoardState;
import br.com.mm.ufpel.fia.exaustiva.util.Element;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Busca em amplitude no quebra-cabeça deslizante
 *
 * @author mertins
 */
public class BreadthFirstSearch {

    private final Board board;
    private final BoardState beginState;
    private final int collectionLimit;
    private final boolean isShuffle;

    /**
     * Construtor para realizar busca em amplitude no quebra-cabeça deslizante
     *
     * @param size tamanho do tabuleiro
     * @param shuffle quantidade de embaralhamento das peças
     * @param collectionLimit limite máximo de elementos na collection
     * @param isShuffle embaralhar qual candidato é visitável primeiro (reduz a
     * repetição de ir e vir da mesma peça)
     */
    public BreadthFirstSearch(int size, int shuffle, int collectionLimit, boolean isShuffle) {
        this.board = new Board(size);
        this.beginState = this.board.shuffle(shuffle);
        this.collectionLimit = collectionLimit;
        this.isShuffle = isShuffle;
    }

    public List<BoardState> run() {
        Queue<BoardState> lista = new LinkedList<>();
        lista.add(beginState);
        while (!lista.isEmpty()) {
            if (lista.size() > this.collectionLimit) {
                System.out.printf("Falha! Collection com muitos elementos[%s]\n ", lista.size());
                throw new RuntimeException("Collection com muitos elementos");
            }
            BoardState testState = lista.poll();
            this.board.print(testState);
            if (!this.board.isTheSolution(testState)) {
                Element[] findCandidates = this.board.findCandidates(testState, isShuffle);
                for (Element possibilidade : findCandidates) {
                    BoardState move = this.board.move(possibilidade, testState);
                    move.setHeight(testState.getHeight() + 1);
                    move.setFather(testState);
                    lista.add(move);
                }
            } else {
                System.out.printf("Fim com sucesso! Elementos restantes na Collection[%d]\n", lista.size());
                return this.makeSolution(testState);
            }
        }
        System.out.printf("Falha! Não encontrou solução\n ");
        throw new RuntimeException("Falha! Não encontrou solução");
    }
    
    
    private List<BoardState> makeSolution(BoardState estadoFinal){   //arrumar teste para montar soluçao
        List<BoardState> solucao = new ArrayList<>();
        while (estadoFinal.getFather()!=null){
            solucao.add(estadoFinal);
            estadoFinal=estadoFinal.getFather();
        }
        return solucao;
    }
}
