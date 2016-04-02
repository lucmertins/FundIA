package br.com.mm.ufpel.fia.exaustiva;

import br.com.mm.ufpel.fia.exaustiva.util.Elemento;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mertins
 */
public class Tabuleiro {

    private final int tamanho;
    private int[][] tabuleiro;
    private int espX;
    private int espY;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.build();
    }

    public void print() {
        for (int i = 0; i < this.tamanho; i++) {
            for (int j = 0; j < this.tamanho; j++) {
                System.out.printf("%d\t", tabuleiro[i][j]);
            }
            System.out.println();
        }
    }

    public void shuffle(int numVezes) {
        for (int i = 0; i < numVezes; i++) {
            Elemento[] candidatos = localizarCandidatos();
            Random gerador = new Random();
            int escolhido = gerador.nextInt(candidatos.length);
            this.mover(candidatos[escolhido]);
        }
    }

    public void mover(Elemento elemento) {
        tabuleiro[this.espX][this.espY] = elemento.getValor();
        tabuleiro[elemento.getX()][elemento.getY()] = 0;
        this.espX = elemento.getX();
        this.espY = elemento.getY();
    }

    public Elemento[] localizarCandidatos() {
        List<Elemento> resp = new ArrayList<>();
        int x = this.espX - 1;
        int y = this.espY;
        if (x > -1) {
            resp.add(new Elemento(x, y, this.tabuleiro[x][y]));
        }
        x = this.espX + 1;
        if (x < this.tamanho) {
            resp.add(new Elemento(x, y, this.tabuleiro[x][y]));
        }
        x = this.espX;
        y = this.espY - 1;
        if (y > -1) {
            resp.add(new Elemento(x, y, this.tabuleiro[x][y]));
        }
        y = this.espY + 1;
        if (y < this.tamanho) {
            resp.add(new Elemento(x, y, this.tabuleiro[x][y]));
        }
        Elemento[] resp2 = new Elemento[resp.size()];
        resp2 = resp.toArray(resp2);

        return resp2;
    }

    private void build() {
        tabuleiro = new int[this.tamanho][this.tamanho];
        int peca = 1;
        for (int i = 0; i < this.tamanho; i++) {
            for (int j = 0; j < this.tamanho; j++) {
                tabuleiro[i][j] = peca++;
            }
        }
        this.espX = this.tamanho - 1;
        this.espY = this.tamanho - 1;
        tabuleiro[this.espX][this.espY] = 0;
    }

}
