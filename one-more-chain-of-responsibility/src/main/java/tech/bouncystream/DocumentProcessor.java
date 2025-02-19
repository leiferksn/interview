package tech.bouncystream;

public interface DocumentProcessor {
    Document process(Document doc);
    void setNextContainer(DocumentProcessor documentProcessor);
}
