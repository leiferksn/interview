package tech.bouncystream;

public class Queue {

    QueueNode first;
    int size = 0;

    void enqueue(QueueNode qn) {
        if (first == null) {
            this.first = qn;
        } else {
            qn.setNext(first);
            first.setPrev(qn);
            this.first = qn;
        }
        size++;
    }

    QueueNode dequeue() {
        QueueNode last;
        if (first.next() == null) {
            last  = first;
            first = null;
        } else {
            last = last(first);
            final var beforeLast = last.prev();
            last.setPrev(null);
            beforeLast.setNext(null);
        }
        size--;
        return last;
    }

    QueueNode last(QueueNode qn) {
        if (qn.next() != null) {
            return last(qn.next());
        }
        return qn;
    }

    int size() {
        return this.size;
    }

}
