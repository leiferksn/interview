package tech.bouncystream;

import java.util.HashMap;
import java.util.Map;

public class MainProcessor extends CompositeDocumentProcessor<ProcessType> {

    private Map<ProcessType, DocumentProcessor> processors = new HashMap<>();

    @Override
    public Document process(Document doc) {
        doStuff();
        nextProcessor(doc.properties().processType());
        return this.documentProcessor.process(doc);
    }

    @Override
    public Map<ProcessType, DocumentProcessor> processors() {
        return processors;
    }

    @Override
    public String toString() {
        return "Main Processor";
    }

    @Override
    public void addProcessor(ProcessType processType, DocumentProcessor documentProcessor) {
        this.processors.put(processType, documentProcessor);
    }
}
