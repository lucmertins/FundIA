package br.com.mertins.ufpel.fia.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mertins
 */
public class Observator {

    public enum ALGORITHMS {
        DFS, BFS, IDS, ASTAR, MINIMAX
    }
    private boolean run = true;
    private final ALGORITHMS algorithm;
    private final int size;
    private final int shuffle;
    private final List<Event> eventos = new ArrayList();
    private long changePath = -1;
    private int height = -1;
    private long hashColision = 0;

    public Observator(ALGORITHMS algorithm, int size, int shuffle) {
        this.algorithm = algorithm;
        this.size = size;
        this.shuffle = shuffle;
        this.eventos.add(new Event("Begin "));
        Thread th = new Thread(() -> {
            while (run) {
                try {
                    Thread.sleep(10000);
                    Runtime.getRuntime().gc();
                    informe();
                } catch (InterruptedException ex) {
                    run = false;
                    Logger.getLogger(Observator.class.getName()).log(Level.SEVERE, "falha no sleep observator", ex);
                }

            }
        });
        th.setDaemon(true);
        th.start();

    }

    public ALGORITHMS getAlgorithm() {
        return algorithm;
    }

    public int getSize() {
        return size;
    }

    public int getShuffle() {
        return shuffle;
    }

    public long getChangePath() {
        return changePath;
    }

    public void setChangePath(long changePath) {
        this.changePath = changePath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getHashColision() {
        return hashColision;
    }

    public void setHashColision(long hashColision) {
        this.hashColision = hashColision;
    }

    public List<Event> getEvents() {
        return eventos;
    }

    public void okSolution() {
        this.eventos.add(new Event("Sucess"));
        this.run = false;
    }

    public void errSolution(int heigth) {
        this.height = heigth;
        this.eventos.add(new Event("Fault "));
        this.run = false;
    }

    public Duration difference() {
        if (this.eventos.size() > 0) {
            Event begin = this.eventos.get(0);
            Event end = this.eventos.get(this.eventos.size() - 5);
            Duration duracao = Duration.between(begin.getInstant(), end.getInstant());
            return duracao;
        }
        return null;
    }

    public boolean isTimeOver() {
        if (this.eventos.isEmpty()) {
            return false;
        } else {
            Event begin = this.eventos.get(0);
            Duration duration = Duration.between(begin.getInstant(), Instant.now());
            return duration.toMinutes() > 4;
        }
    }

    private String informe() {
        Event begin = this.eventos.get(0);
        Duration duration = Duration.between(begin.getInstant(), Instant.now());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String format = fmt.format(duration.addTo(LocalDateTime.of(0, 1, 1, 0, 0)));
        System.out.printf("Execução [%s] Troc Path [%d] Height [%d] hashColisioin [%d] MemFree [%dM]\n", format, changePath, height, hashColision, Runtime.getRuntime().freeMemory() / 1048576);
        return format;
    }
}
