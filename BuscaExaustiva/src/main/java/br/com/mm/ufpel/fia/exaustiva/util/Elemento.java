package br.com.mm.ufpel.fia.exaustiva.util;

/**
 *
 * @author mertins
 */
public class Elemento {

    private final int x;
    private final int y;
    private final int valor;

    public Elemento(int y, int x, int valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.x;
        hash = 23 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Elemento other = (Elemento) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}
