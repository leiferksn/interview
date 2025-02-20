package tech.bouncystream;

public class WriteProcessor extends SimpleDocumentProcessor {

    @Override
    public Document process(Document doc) {
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
