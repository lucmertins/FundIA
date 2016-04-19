package br.com.mertins.ufpel.competitiva;

import br.com.mertins.ufpel.competitiva.util.BoardTicTacToe;
import br.com.mertins.ufpel.fia.util.BoardState;
import br.com.mertins.ufpel.fia.util.Observator;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author mertins
 */
public class MiniMax {

    private final Observator observator;
    protected final BoardTicTacToe board;

    public MiniMax(Observator observator) {
        this.observator = observator;
        this.board = new BoardTicTacToe();
    }

    public List<BoardState> run() {

        return null;
    }

    public void print(List<BoardState> lista) {
        this.board.print();
    }

    public void print(BoardState boardState) {
        this.board.print();
    }

    public String time(Duration duration) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String format = fmt.format(duration.addTo(LocalDateTime.of(0, 1, 1, 0, 0)));
        return format;
    }
}
/*
function minimax(node, depth, maximizingPlayer)
     if depth = 0 or node is a terminal node
         return the heuristic value of node

     if maximizingPlayer
         bestValue := −∞
         for each child of node
             v := minimax(child, depth − 1, FALSE)
             bestValue := max(bestValue, v)
         return bestValue

     else    (* minimizing player *)
         bestValue := +∞
         for each child of node
             v := minimax(child, depth − 1, TRUE)
             bestValue := min(bestValue, v)
         return bestValue 


ROTINA minimax(nó, profundidade)
    SE nó é um nó terminal OU profundidade = 0 ENTÃO
        RETORNE o valor da heurística do nó
    SENÃO SE o nó representa a jogada de algum adversário ENTÃO
        α ← +∞
        PARA CADA filho DE nó
            α ← min(α, minimax(filho, profundidade-1))
        FIM PARA
        RETORNE α
    SENÃO
        α ← -∞
        PARA CADA filho DE nó
            α ← max(α, minimax(filho, profundidade-1))
        FIM PARA
        RETORNE α
    FIM SE
FIM ROTINA
 */
