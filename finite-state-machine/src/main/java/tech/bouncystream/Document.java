package tech.bouncystream;

public class Document {
    private State state;

    public Document(State initialState) {
        this.state = initialState;
    }

    void changeState(State state) {
        this.state = state;
    }

    public State state() {
        return this.state;
    }
}
