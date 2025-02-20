package tech.bouncystream;

import java.util.Map;

public abstract class CompositeDocumentProcessor<T> implements DocumentProcessor {

    protected DocumentProcessor documentProcessor;

    @Override
    public abstract Document process(Document doc);

    public void nextProcessor(T parameter) {
        this.documentProcessor = processors().get(parameter);
    }

    public abstract Map<T, DocumentProcessor> processors();

    public abstract void addProcessor(T t, DocumentProcessor documentProcessor);

}
