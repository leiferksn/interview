package tech.bouncystream;

public class BaseProcessor implements DocumentProcessor {

    private ContainerProcessor containerProcessor;

    @Override
    public Document process(Document doc) {
        doStuff();
        return doc;
    }

    void setContainer(ContainerProcessor containerProcessor) {
        this.containerProcessor = containerProcessor;
    }

    ContainerProcessor container() {
        return this.containerProcessor;
    }

    public void doStuff() {
        System.out.println("Doing stuff with " + this);
    }
}
