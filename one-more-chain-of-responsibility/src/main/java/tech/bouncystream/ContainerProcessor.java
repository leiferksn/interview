package tech.bouncystream;

import java.util.ArrayList;
import java.util.List;

public abstract class ContainerProcessor extends BaseProcessor {

    private List<BaseProcessor> processors = new ArrayList<>();

    @Override
    public Document process(Document doc) {
        super.doStuff();
        // based on the document properties decide which processor to take
        return this.processors.get(processorIdx()).process(doc);
    }

    void addProcessor(BaseProcessor processor) {
        processors.add(processor);
        processor.setContainer(this);
    }

    public abstract Integer processorIdx();





}
