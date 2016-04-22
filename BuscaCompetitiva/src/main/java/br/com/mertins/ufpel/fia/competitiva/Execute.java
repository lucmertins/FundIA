package br.com.mertins.ufpel.fia.competitiva;

import br.com.mertins.ufpel.fia.competitiva.util.Node;
import br.com.mertins.ufpel.fia.competitiva.util.Observator;
import static br.com.mertins.ufpel.fia.competitiva.util.Observator.ALGORITHMS.MINIMAX;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void miniMax(final Observator observador, final int depth) {
        new Thread(() -> {
            MiniMax search = new MiniMax(observador, depth);
            try {
                Node solucao = search.run();
//                System.out.println("***************** Solução **********");
//                search.print(solucao);
//                System.out.printf("***************** Movimentos [%d]\n", solucao.size());
            } catch (Exception ex) {
                System.out.println("***************** Falha **********");
                ex.printStackTrace();
            }
//            for (Event evento : observador.getEvents()) {
//                System.out.println(evento.toString());
//            }
            System.out.printf("\nTempo de execução [%s]\n", search.time(observador.difference()));
            System.out.printf("Vitorias [%s]  Derrotas[%s]  Empates [%s]\n", observador.getVitorias(), observador.getDerrotas(), observador.getEmpates());
            System.out.printf("ValorHeadPositivo [%s]  ValorHeadNegativo [%s]  ValorHeadZero [%s]\n", observador.getValorHeadPositivo(), observador.getValorHeadNegativo(), observador.getValorHeadZero());

        }).start();
    }

    public static void main(String[] args) {
        Observator.ALGORITHMS opcao = Observator.ALGORITHMS.MINIMAX;
        int depth = 9;
        if (args.length == 2) {
            try {
                opcao = Observator.ALGORITHMS.valueOf(args[0]);
                depth = Integer.valueOf(args[1]);
            } catch (Exception ex) {
                System.out.println("usar: MINIMAX profundidadeBusca");
                System.out.println("Falha");
                ex.printStackTrace();
                System.exit(1);
            }
        }
        System.out.printf("Algoritmo[%s] Profundidade [%d]\n ", opcao.toString(), depth);
        Observator observador = new Observator(opcao);
        switch (opcao) {
            case MINIMAX:
                Execute.miniMax(observador, depth);
                break;

        }

    }
}
