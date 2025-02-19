package tech.bouncystream;

public class SimpleProcessor implements DocumentProcessor {

    private DocumentProcessor nextDocumentProcessor;


    @Override
    public Document process(Document doc) {
        doStuff();
        if (nextContainer() != null) {
            nextContainer().process(doc);
        }
        return doc;
    }

    public void doStuff() {
        System.out.println("Doing stuff with " + this);
    }

    @Override
    public void setNextContainer(DocumentProcessor documentProcessor) {
        this.nextDocumentProcessor = documentProcessor;
    }

    public DocumentProcessor nextContainer() {
        return this.nextDocumentProcessor;
    }

}
