package tech.bouncystream;

public class ReadyToProcessState extends State {

    public ReadyToProcessState(Document doc) {
        super(doc);
    }

    @Override
    public void process() {
        // do something in this state or simply go to the next
        super.process();
        final var nextState = new PreProcessState(this.getDocument());
        this.getDocument().changeState(nextState);
    }

    @Override
    public Document getDocument() {
        return super.getDocument();
    }

    @Override
    public String toString() {
        return "READY_TO_PROCESS";
    }
}
