package tech.bouncystream;

class QueueNode {

    private final String content;
    private QueueNode prev;
    private QueueNode next;

    QueueNode(String content) {
        this.content = content;
    }

    QueueNode prev() {
        return this.prev;
    }

    QueueNode next() {
        return this.next;
    }

    void setNext(QueueNode next) {
        this.next = next;
    }

    void setPrev(QueueNode prev) {
        this.prev = prev;
    }

    String content() {
        return this.content;
    }


}
