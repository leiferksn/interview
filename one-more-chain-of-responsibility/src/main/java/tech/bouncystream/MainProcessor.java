package tech.bouncystream;

public class MainProcessor extends CompositeDocumentProcessor<ProcessType> {


    @Override
    public RawDocument process(RawDocument doc) {
        doStuff();
        nextProcessor(doc.processProperties().processType());
        return this.documentProcessor.process(doc);
    }

    @Override
    public String toString() {
        return "Main Processor";
    }
}
