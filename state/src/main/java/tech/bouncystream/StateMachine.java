package tech.bouncystream;

public class StateMachine {

    public static void main(final String args[]) {
        final var doc = new Document();
        doc.changeState(new PreProcessState(doc));
        doc.process();
    }
}