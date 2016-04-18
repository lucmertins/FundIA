package br.com.mertins.ufpel.competitiva.util;

/**
 *
 * @author mertins
 */
public class Node {

    public enum Infinite {
        POSITIVE, NEGATIVE
    }
    private final Long value;
    private final Infinite infinite;

    public Node(Long value) {
        this.value = value;
        this.infinite = null;
    }

    public Node(Infinite infinite) {
        this.infinite = infinite;
        this.value = null;
    }

    public Long getValue() {
        return value;
    }

    public Infinite getInfinite() {
        return infinite;
    }

}
