package tech.bouncystream;

public class QueueApp {

    public static void main(String [] args) {
        final var q = new Queue();
        q.enqueue(new QueueNode("hey message"));
        q.enqueue(new QueueNode("one more"));
        q.enqueue(new QueueNode("not again"));

        final var msg = q.dequeue();
        System.out.println(msg.content());

        q.enqueue(new QueueNode("never again"));

        final var oneMore = q.dequeue();
        System.out.println(oneMore.content());

        final var notAgain = q.dequeue();
        System.out.println(notAgain.content());

        q.enqueue(new QueueNode("junk"));
        System.out.println(q.size());
    }

}
