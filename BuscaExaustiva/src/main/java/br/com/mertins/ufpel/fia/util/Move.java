package br.com.mertins.ufpel.fia.util;

/**
 *
 * @author mertins
 */
public class Move {
    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
    
    private final int valueChange;
    private final Direction direction;

    public Move(int valueChange, Direction direction) {
        this.valueChange = valueChange;
        this.direction = direction;
    }

    public int getValueChange() {
        return valueChange;
    }

    public Direction getDirection() {
        return direction;
    }
    
}
