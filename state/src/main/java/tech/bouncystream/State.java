package tech.bouncystream;

public abstract class State implements Processable {

    private Document document;

    public State(final Document doc) {
        this.document = doc;
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public void process() {
        System.out.println("Do something with document in state: " + this);
    }
}
