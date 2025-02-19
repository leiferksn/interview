package tech.bouncystream;

import java.util.Random;

public class MainProcessor extends ContainerProcessor {

    @Override
    public Document process(Document doc) {
        return super.process(doc);
    }

    @Override
    public String toString() {
        return "Main Processor";
    }

    @Override
    public Integer processorIdx() {
        final var random = new Random();
        return random.ints(0, 100).findFirst().getAsInt() % 2 == 0 ? 0 : 1;
    }
}
