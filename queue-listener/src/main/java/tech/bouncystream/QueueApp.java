package tech.bouncystream;

public class QueueApp {

    public static void main(String[] args) throws InterruptedException {

        final var q = new Queue();
        q.enqueue(new QueueNode("hey message"));
        q.enqueue(new QueueNode("one more"));
        q.enqueue(new QueueNode("not again"));

        final var msg = q.dequeue();
        printMessage(msg);
        q.enqueue(new QueueNode("never again"));

        final var oneMore = q.dequeue();
        printMessage(oneMore);

        final var notAgain = q.dequeue();
        printMessage(notAgain);

        q.enqueue(new QueueNode("junk"));
        System.out.println(q.size());

        for (int i = 0; i < 100000; i++) {
            q.enqueue(new QueueNode("element " + i));
        }

        System.out.println(q.size());

        final var listener = new QueueListener(q, "casual-listener");
        q.registerListener(listener);

        final var nextListener = new QueueListener(q, "yet another listener");
        q.registerListener(nextListener);

        q.enqueue(new QueueNode("to-all-listeners"));
        Thread.sleep(2000);
        // q.unregisterListener(listener);
        q.enqueue(new QueueNode("hi hi hi"));
    }

    static void printMessage(QueueNode qn) {
        System.out.println(qn != null ? qn : "");
    }
}
