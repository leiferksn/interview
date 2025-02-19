package tech.bouncystream;

public class ProcessState extends State {

    public ProcessState(Document doc) {
        super(doc);
    }

    @Override
    public State next() {
        System.out.println("Changing to write state");
        final var nextState = new WriteState(this.getDocument());
        this.getDocument().changeState(nextState);
        return nextState;
    }

    @Override
    public String toString() {
        return "PROCESS";
    }
}
