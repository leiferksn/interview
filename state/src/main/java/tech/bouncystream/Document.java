package tech.bouncystream;

public class Document {

    private State state;

    void process() {
        while (this.state !=null) {
            state.next();
        }
    }

    public void changeState(final State state) {
        System.out.println("Setting state: " + state);
        this.state = state;
    }

}
