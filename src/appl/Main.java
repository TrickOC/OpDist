package appl;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> input = new LinkedList<>();
        Random r = new Random();
        long tempo_single, tempo_multi, soma;

        for (int i = 0; i < 10000000; ++i)
            input.add(r.nextInt(1000));

        System.out.println("Soma single thread com map e sum:");
        tempo_single = System.nanoTime();
        soma = input.stream().mapToLong(Integer::longValue).sum();
        tempo_single = System.nanoTime() - tempo_single;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempo_single + "ns");
        System.out.println();

        System.out.println("Soma multi threads com map e sum:");
        tempo_multi = System.nanoTime();
        soma = input.parallelStream().mapToLong(Integer::longValue).sum();
        tempo_multi = System.nanoTime() - tempo_multi;
        System.out.println("Total da soma: " + soma);
        System.out.println("Tempo da soma: " + tempo_multi + "ns");

        System.out.println();
        System.out.println("Diferenca de tempo(Multi - Single): " + (tempo_multi - tempo_single) + "ns");
    }
}
