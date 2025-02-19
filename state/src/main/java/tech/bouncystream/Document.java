package tech.bouncystream;

public class Document implements Processable {

    private State state;

    public Document() {
        System.out.println("Init state to READY_TO_PROCESS");
        this.state = new ReadyToProcessState(this);
    }

    @Override
    public void process() {
        while(!(this.state instanceof WrittenState))  {
            state.process();
        }
    }

    public void changeState(final State state) {
        System.out.println("Setting state: " + state);
        this.state = state;
    }
}
