package tech.bouncystream;

public class WrittenState extends State {

    public WrittenState(final Document doc) {
        super(doc);
    }

    @Override
    public void process() {
        // do something in this state, simply go to the next or don't do anything
    }

    @Override
    public String toString() {
        return "WRITTEN_STATE";
    }
}
