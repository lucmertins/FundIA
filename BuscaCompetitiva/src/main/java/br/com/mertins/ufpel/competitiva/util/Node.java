package br.com.mertins.ufpel.competitiva.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mertins
 */
public class Node {

    public enum Infinite {
        POSITIVE, NEGATIVE
    }

    public enum Marker {
        X, O, B;

        public String toString() {
            switch (this) {
                case X:
                    return "X";
                case O:
                    return "0";
                default:
                    return "H";
            }
        }

        public char toChar() {
            switch (this) {
                case X:
                    return 'X';
                case O:
                    return '0';
                default:
                    return 'H';
            }
        }
        public char toByte() {
            switch (this) {
                case X:
                    return 1;
                case O:
                    return 2;
                default:
                    return 0;
            }
        }
    }
    private final Long value;
    private final Marker marker;
    private final Infinite infinite;
    private final List<Node> children = new ArrayList<>();
    private Node parent = null;
    private byte posX;
    private byte posY;

    public Node(Long value, Marker marker) {
        this.value = value;
        this.infinite = null;
        this.marker = marker;
    }

    public Node(Infinite infinite, Marker marker) {
        this.infinite = infinite;
        this.value = null;
        this.marker = marker;
    }

    public Long getValue() {
        return value;
    }

    public Infinite getInfinite() {
        return infinite;
    }

    public Marker getMarker() {
        return marker;
    }

    public List<Node> getChildren() {
        return children;
    }

    public byte getPosX() {
        return posX;
    }

    public void setPosX(byte posX) {
        this.posX = posX;
    }

    public byte getPosY() {
        return posY;
    }

    public void setPosY(byte posY) {
        this.posY = posY;
    }

    public Node max(Node other) {
        return this.value >= other.value ? this : other;
    }

    public Node min(Node other) {
        return this.value < other.value ? this : other;
    }

    public void addChild(Node node) {
        node.parent = this;
        children.add(node);
    }

    public Node getParent() {
        return parent;
    }
    
}
