package tech.bouncystream;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadLocalExample implements Runnable {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<>() {
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int get() {
        return threadId.get();
    }

    @Override
    public void run() {
        System.out.println(ThreadLocalExample.get());
    }

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> {
            final var threadId = new ThreadLocalExample();
            final var t = new Thread(threadId);
            t.start();
        });
    }
}