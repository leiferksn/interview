package tech.bouncystream;

public class WriteProcessor extends SimpleDocumentProcessor {

    @Override
    public RawDocument process(RawDocument doc) {
        doStuff();
        if (this.documentProcessor != null) {
            return this.documentProcessor.process(doc);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Write Processor";
    }
}
