package tech.bouncystream;

public class WriteState extends State {


    public WriteState(Document doc) {
        super(doc);
    }

    @Override
    public State next() {
        System.out.println("End state.");
        this.getDocument().changeState(null);
        return null;
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}
