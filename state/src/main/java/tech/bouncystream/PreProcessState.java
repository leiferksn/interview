package tech.bouncystream;

public class PreProcessState extends State{

    public PreProcessState(Document doc) {
        super(doc);
    }

    @Override
    public void process() {
        super.process();
        final var nextState = new ProcessState(this.getDocument());
        this.getDocument().changeState(nextState);
    }

    @Override
    public String toString() {
        return "PRE_PROCESS";
    }
}
