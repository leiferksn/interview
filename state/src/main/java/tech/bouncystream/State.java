package tech.bouncystream;

public abstract class State {

    private Document document;

    public State(final Document doc) {
        this.document = doc;
    }

    public abstract State next();

    public Document getDocument() {
        return document;
    }

}
