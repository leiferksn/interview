package tech.bouncystream;

public class Counter implements Runnable {

    private int c = 0;

    public void increment() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException iex) {
            System.err.println("Wasn't finished.");
        }
        this.c++;
    }


    public void decrement() {
        this.c--;
    }

    public int value() {
        return this.c;
    }

    @Override
    public void run() {
        synchronized (this) {
            this.increment();
            System.out.println("Value for Thread After increment " + Thread.currentThread().getName() + " " + this.value());
            this.decrement();
            System.out.println("Value for Thread at last " + Thread.currentThread().getName() + " " + this.value());
        }

        /**
         * Value for Thread After increment Thread-1 1
         * Value for Thread at last Thread-1 0
         * Value for Thread After increment Thread-3 1
         * Value for Thread at last Thread-3 0
         * Value for Thread After increment Thread-2 1
         * Value for Thread at last Thread-2 0
         */

    }


}
