package tech.bouncystream;

public class Document {
    private State state;

    void changeState(State state) {
        this.state = state;
    }

    public State state() {
        return this.state;
    }
}
