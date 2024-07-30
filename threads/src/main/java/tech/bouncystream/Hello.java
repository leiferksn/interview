package tech.bouncystream;

public class Hello implements Runnable {

    private Counter counter;
    private int wait;
    private int idx;

    public Hello(int idx, int wait, Counter c) {
        this.idx = idx;
        this.wait = wait;
        counter = c;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.wait);
            synchronized (this) {
                this.counter.increment();
                System.out.println("Hello, incremented... Value is: " + this.counter.value());

                this.counter.decrement();
                System.out.println("Hello, decremented... Value is: " + this.counter.value());
            }
        } catch (InterruptedException iex) {
            System.err.println(idx + ". Wasn't finished.");
        }
    }
}
