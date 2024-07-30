package tech.bouncystream;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class ThreadExamples {

    public static void main(String[] args) {

        final var threads = new ArrayList<Thread>();
        long startTime = System.currentTimeMillis();

        final var r = new Random();
        final int idx = r.nextInt(600, 650);
        final var counter = new Counter();
        final var hello = new Hello(idx, idx, counter);

        final var patience = 25000;
        IntStream.range(0, 50).forEach(i -> {
            final var t = new Thread(hello);
            t.start();
            System.out.println("STATE of " + t.getName() + ": " + t.getState());
            threads.add(t);
        });

        threads.forEach(t -> {
            while (t.isAlive()) {
                System.out.println("Waiting... " + t.getName());
                try {
                    t.join(10000);
                    if ((System.currentTimeMillis() - startTime) > patience) {
                        System.out.println("Can't wait anymore. You're too slow. " + t.getName());
                        t.interrupt();
                    }
                } catch (InterruptedException iex) {
                    System.out.println("Forced finish. STATE: " + t.getState());
                } finally {
                    System.out.println("STATE: " + t.getState());
                }
            }
        });

    }
}