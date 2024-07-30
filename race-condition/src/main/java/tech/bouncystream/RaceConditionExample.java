package tech.bouncystream;

public class RaceConditionExample {
    public static void main(String[] args) {
        final var counter = new Counter();
        final var t1 = new Thread(counter, "Thread-1");
        final var t2 = new Thread(counter, "Thread-2");
        final var t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}