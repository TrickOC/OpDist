package appl;

import java.util.*;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        List<Integer> input = new LinkedList<>();
        Random r = new Random(4228);
        long initial, soma;
        long[] tempos = new long[4];

        for (int i = 0; i < 1000000; ++i)
            input.add(r.nextInt(100));

        System.out.println("Soma single thread com map e sum:");
        initial = System.nanoTime();
        soma = input.stream().mapToLong(Integer::longValue).sum();
        tempos[0] = System.nanoTime() - initial;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempos[0] + "ns");
        System.out.println();

        System.out.println("Soma multi threads com map e sum:");
        initial = System.nanoTime();
        soma = input.parallelStream().mapToLong(Integer::longValue).sum();
        tempos[1] = System.nanoTime() - initial;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempos[1] + "ns");
        System.out.println();

        System.out.println("Soma single thread com reduce e sum:");
        initial = System.nanoTime();
        soma = input.stream().reduce(0, Integer::sum);
        tempos[2] = System.nanoTime() - initial;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempos[2] + "ns");
        System.out.println();

        System.out.println("Soma multi threads com reduce e sum:");
        initial = System.nanoTime();
        soma = input.parallelStream().reduce(0, Integer::sum);
        tempos[3] = System.nanoTime() - initial;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempos[3] + "ns");
        System.out.println();

        int idx_menor = (int) LongStream.range(0, tempos.length).reduce(0,Math::min);
        System.out.println("Menor tempo: " + tempos[idx_menor] + "ns");

    }
}
