package tech.bouncystream;

public class QueueListener {

    private Queue queue;
    private final String name;

    QueueListener(final Queue q, final String name) {
        this.queue = q;
        this.name = name;
    }

    void processQueueMessage(final String msg) {
        final var listenerName = this.name;
        Thread.startVirtualThread( () -> {
            if (msg != null) {
                System.out.println("Processing message: '" + msg +  " with listener " + listenerName + " in thread: " + Thread.currentThread() + "' ...");
            } else {
                System.out.println("Listener: " + this + " Queue is empty. Thread: " + Thread.currentThread());
            }
        });


    }

    String name() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
