package tech.bouncystream;

public class MainProcessor extends CompositeDocumentProcessor<ProcessType> {


    @Override
    public void process(RawDocument doc) {
        doStuff(doc);
        nextProcessor(doc.processProperties().processType());
        this.documentProcessor.process(doc);
    }

    @Override
    public String toString() {
        return "Main Processor";
    }
}
