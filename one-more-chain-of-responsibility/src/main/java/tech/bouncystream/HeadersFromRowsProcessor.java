package tech.bouncystream;

public class HeadersFromRowsProcessor extends SimpleDocumentProcessor {

    @Override
    public void nextProcessor(DocumentProcessor documentProcessor) {
        super.nextProcessor(documentProcessor);
    }

    @Override
    public Document process(Document doc) {
        doStuff();
        return this.documentProcessor.process(doc);
    }

    @Override
    public String toString() {
        return "Header from Rows Processor";
    }
}
