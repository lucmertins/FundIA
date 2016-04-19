package br.com.mertins.ufpel.competitiva;

import br.com.mertins.ufpel.competitiva.util.BoardTicTacToe;
import br.com.mertins.ufpel.competitiva.util.Node;
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

    public Node run() {
        minimax(board.getNodeBegin(), 20, true);
        return board.getNodeBegin();
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

    private Node minimax(Node node, int depth, boolean maximizingPlayer) {
        if (depth == 0||this.board.terminal(node)) {
            return this.board.heuristic(node);
        } else if (maximizingPlayer) {
            Node bestValue = new Node(Node.Infinite.NEGATIVE, Node.Marker.X);
            for (Node nodeChild : this.board.findCandidates(node)) {
                Node temp = minimax(nodeChild, depth - 1, false);
                bestValue = bestValue.max(temp);
            }
            return bestValue;
        } else {
            Node bestValue = new Node(Node.Infinite.POSITIVE,Node.Marker.O);
            for (Node nodeChild : this.board.findCandidates(node)) {
                Node temp = minimax(nodeChild, depth - 1, true);
                bestValue = bestValue.min(temp);
            }
            return bestValue;
        }
    }

}
