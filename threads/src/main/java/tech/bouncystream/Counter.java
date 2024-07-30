package tech.bouncystream;

public class Counter {

    private int c = 0;

    public synchronized void increment() {
        this.c++;
    }

    public synchronized void decrement() {
        this.c--;
    }

    public int value() {
        return this.c;
    }

}
