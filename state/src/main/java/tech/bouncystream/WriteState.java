package tech.bouncystream;

public class WriteState extends State {


    public WriteState(Document doc) {
        super(doc);
    }

    @Override
    public void process() {
        super.process();
        this.getDocument().changeState(new WrittenState(this.getDocument()));
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}
