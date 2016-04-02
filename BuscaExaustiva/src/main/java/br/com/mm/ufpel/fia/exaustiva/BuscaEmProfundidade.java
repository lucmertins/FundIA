package br.com.mm.ufpel.fia.exaustiva;

/**
 *
 * @author mertins
 */
public class BuscaEmProfundidade {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(3);
        tabuleiro.print();
        System.out.println();
        tabuleiro.shuffle(1000);
        tabuleiro.print();
    }
}
