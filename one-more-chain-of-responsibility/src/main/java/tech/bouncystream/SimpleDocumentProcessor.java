package tech.bouncystream;

public abstract class SimpleDocumentProcessor implements DocumentProcessor {

    protected DocumentProcessor documentProcessor;

    @Override
    public abstract Document process(Document doc);

    public void nextProcessor(DocumentProcessor documentProcessor) {
        this.documentProcessor = documentProcessor;
    }
}
