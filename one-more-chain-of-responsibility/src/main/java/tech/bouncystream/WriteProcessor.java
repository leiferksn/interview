package tech.bouncystream;

public class WriteProcessor extends SimpleDocumentProcessor {

    @Override
    public void process(RawDocument doc) {
        doStuff(doc);
    }

    @Override
    public String toString() {
        return "Write Processor";
    }
}
