package tech.bouncystream;

public class QueueListener {

    private Queue queue;
    private final String name;

    QueueListener(final Queue q, final String name) {
        this.queue = q;
        this.name = name;
    }

    void processQueueMessage(final String msg) {
        if (msg != null) {
            System.out.println("Processing message: '" + msg + "' ... in thread: " + Thread.currentThread());
        } else {
            System.out.println("Queue is empty. Thread: " + Thread.currentThread());
        }
    }

    String name() {
        return this.name;
    }

}
