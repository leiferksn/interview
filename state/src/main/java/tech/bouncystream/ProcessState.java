package tech.bouncystream;

public class ProcessState extends State {

    public ProcessState(Document doc) {
        super(doc);
    }

    @Override
    public void process() {
        super.process();
        final var nextState = new WriteState(this.getDocument());
        this.getDocument().changeState(nextState);
    }

    @Override
    public String toString() {
        return "PROCESS";
    }

}
