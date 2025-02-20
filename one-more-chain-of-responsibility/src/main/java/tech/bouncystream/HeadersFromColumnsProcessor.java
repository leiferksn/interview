package tech.bouncystream;

public class HeadersFromColumnsProcessor extends SimpleDocumentProcessor {

    @Override
    public Document process(Document doc) {
        doStuff();
        return this.documentProcessor.process(doc);
    }

    @Override
    public void nextProcessor(DocumentProcessor documentProcessor) {
        super.nextProcessor(documentProcessor);
    }

    @Override
    public String toString() {
        return "Header from Cols Processor";
    }
}
