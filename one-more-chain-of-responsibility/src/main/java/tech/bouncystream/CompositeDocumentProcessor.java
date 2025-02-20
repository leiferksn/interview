package tech.bouncystream;

import java.util.HashMap;
import java.util.Map;

public abstract class CompositeDocumentProcessor<T> implements DocumentProcessor {

    protected DocumentProcessor documentProcessor;
    protected Map<T, DocumentProcessor> processors = new HashMap<>();

    @Override
    public abstract Document process(Document doc);

    public void nextProcessor(T parameter) {
        this.documentProcessor = processors().get(parameter);
    }

    public Map<T, DocumentProcessor> processors() {
        return this.processors;
    }

    public  void addProcessor(T t, DocumentProcessor documentProcessor) {
        this.processors.put(t, documentProcessor);
    }

}
