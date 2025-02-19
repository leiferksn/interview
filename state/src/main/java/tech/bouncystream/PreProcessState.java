package tech.bouncystream;

public class PreProcessState extends State{

    public PreProcessState(Document doc) {
        super(doc);
    }

    @Override
    public State next() {
        System.out.println("Changing to process state");
        final var nextState = new ProcessState(this.getDocument());
        this.getDocument().changeState(nextState);
        return nextState;
    }

    @Override
    public String toString() {
        return "PRE_PROCESS";
    }
}
