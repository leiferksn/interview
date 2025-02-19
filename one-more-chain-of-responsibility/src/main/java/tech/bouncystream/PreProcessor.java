package tech.bouncystream;

public class PreProcessor extends BaseProcessor {

    @Override
    public Document process(Document doc) {
        doStuff();
        // then pass the document ot then next in the chain to process it
        final var newDoc = new Document(doc.content(), new Properties(0));
        return container().process(newDoc);
    }

    @Override
    public String toString() {
        return "Pre Processor";
    }

}
