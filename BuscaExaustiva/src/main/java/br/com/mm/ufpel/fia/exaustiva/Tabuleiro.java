package br.com.mm.ufpel.fia.exaustiva;

/**
 *
 * @author mertins
 */
public class Tabuleiro {

    private final int tamanho;
    private int[][] tabuleiro;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.build();
        this.print();

    }

    public void build() {
        tabuleiro = new int[this.tamanho][this.tamanho];
        int peca = 1;
        for (int i = 0; i < this.tamanho; i++) {
            for (int j = 0; j < this.tamanho; j++) {
                tabuleiro[i][j] = peca++;
            }
        }
        tabuleiro[this.tamanho - 1][this.tamanho - 1] = 0;
    }

    public void print() {
        for (int i = 0; i < this.tamanho; i++) {
            for (int j = 0; j < this.tamanho; j++) {
                System.out.printf("%d\t", tabuleiro[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(4);

    }
}
