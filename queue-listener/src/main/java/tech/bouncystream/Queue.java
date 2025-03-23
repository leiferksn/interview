package tech.bouncystream;

import java.util.ArrayList;
import java.util.List;

public class Queue {

    private final List<QueueListener> listeners = new ArrayList<>();

    QueueNode first;
    int size = 0;

    synchronized void enqueue(QueueNode qn) {
        if (first == null) {
            this.first = qn;
        } else {
            qn.setNext(first);
            first.setPrev(qn);
            this.first = qn;
        }
        size++;
        notifyListeners();
    }

    synchronized QueueNode dequeue() {
        QueueNode last;
        if (first == null) {
            System.out.println("Nothing more to dequeue. Thread: " + Thread.currentThread());
            return null;
        }
        if (first.next() == null) {
            last = first;
            first = null;
        } else {
            last = stackOverFlowSafeLast();
            final var beforeLast = last.prev();
            last.setPrev(null);
            beforeLast.setNext(null);
        }
        size--;
        return last;
    }

    // stackoverflow prone !!!
    QueueNode last(QueueNode qn) {
        if (qn.next() != null) {
            return last(qn.next());
        }
        return qn;
    }

    QueueNode stackOverFlowSafeLast() {
        var qn = this.first;
        do {
            qn = qn.next();
        } while (qn.next() != null);
        return qn;
    }

    int size() {
        return this.size;
    }

    void registerListener(final QueueListener ql) {
        this.listeners.add(ql);
    }

    void unregisterListener(final QueueListener ql) {
        final var it = this.listeners.iterator();
        while (it.hasNext()) {
            final var l = it.next();
            if (l.name().equals(ql.name())) {
                this.listeners.remove(l);
                break;
            }
        }
    }

    // TODO
    //  although this works in this particular example, is not a good design, because
    //  it creates a new Thread every time notifyListeners gets called, which is every time we enqueue a new msg.
    //  so in the worst case we can have a 1 thread/msg. thee logic should be fixed to use a thread pool, so that it
    //  reuses existing threads.
    synchronized void notifyListeners() {
        final var listeners = this.listeners;
        if (listeners.size() > 0) {
            // read message only if there are registered listeners. otherwise collect the messages in the queue.
            final var decoupledProcessing = new Thread(() -> {
                System.out.println("Thread: " + Thread.currentThread());
                QueueNode msg;
                do {
                    msg = dequeue();
                    final var content = msg != null ? msg.content() : null;
                    for(QueueListener l : listeners) {
                        l.processQueueMessage(content);
                    }
                } while (msg != null);
            });

            decoupledProcessing.start();
        }
    }
}
