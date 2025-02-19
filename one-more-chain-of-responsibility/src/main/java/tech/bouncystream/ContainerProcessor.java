package tech.bouncystream;

import java.util.ArrayList;
import java.util.List;

public abstract class ContainerProcessor extends SimpleProcessor {

    private List<SimpleProcessor> processors = new ArrayList<>();

    @Override
    public Document process(Document doc) {
        super.doStuff();
        // based on the document properties decide which processor to take
        return nextContainer().process(doc);
    }

    void addProcessor(SimpleProcessor processor) {
        processors.add(processor);
    }

    public abstract Integer processorIdx();

    @Override
    public DocumentProcessor nextContainer() {
        return processors.get(processorIdx());
    }
}
