package tech.bouncystream;

public class Queue {

    QueueNode first;

    void enqueue(QueueNode qn) {
        if (first == null) {
            this.first = qn;
        } else {
            qn.setNext(first);
            first.setPrev(qn);
            this.first = qn;
        }
    }

    QueueNode dequeue() {
        if (first.next() == null) {
            final var last = first;
            first = null;
            return last;
        } else {
            final var last = last(first);
            final var beforeLast = last.prev();
            last.setPrev(null);
            beforeLast.setNext(null);
            return last;
        }

    }

    QueueNode last(QueueNode qn) {
        if (qn.next() != null) {
            return last(qn.next());
        }
        return qn;
    }

}
