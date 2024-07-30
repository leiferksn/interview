package tech.bouncystream;

public class ObserverTest {

    public static void main(String[] args) {
        final var t = new Topic();

        final var o1 = new TopicSubscriber("Observer 1");
        final var o2 = new TopicSubscriber("Observer 2");
        final var o3 = new TopicSubscriber("Observer 3");

        t.register(o1);
        t.register(o2);
        t.register(o3);

        o1.addSubject(t);
        o2.addSubject(t);
        o3.addSubject(t);

        o1.update();

        t.postMessage("Hello!");
    }
}