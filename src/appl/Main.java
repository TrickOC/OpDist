package appl;

import core.MapReduce;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> input = new LinkedList<>();
        Random r = new Random(4228);
        long initial, soma;
        long[] tempos = new long[3];

        for (int i = 0; i < 10000000; ++i)
            input.add(r.nextInt(1000));

        System.out.println("Soma single thread com reduce e sum:");
        initial = System.currentTimeMillis();
        soma = input.stream().reduce(0, Integer::sum, Integer::sum);
        tempos[0] = System.currentTimeMillis() - initial;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempos[0] + "ms");
        System.out.println();

        System.out.println("Soma multi threads com reduce e sum:");
        initial = System.currentTimeMillis();
        soma = input.parallelStream().reduce(0, Integer::sum, Integer::sum);
        tempos[1] = System.currentTimeMillis() - initial;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempos[1] + "ms");
        System.out.println();

        System.out.println("Soma multi threads com map reduce:");
        initial = System.currentTimeMillis();
        MapReduce mr = new MapReduce();
        mr.mapInput(input);
        soma = mr.parallelReduce();
        tempos[2] = System.currentTimeMillis() - initial;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempos[2] + "ms");
        System.out.println();

        long min_tempo = Arrays.stream(tempos).min().getAsLong();
        System.out.println("Menor tempo: " + min_tempo + "ms");
    }
}
