package tech.bouncystream;

import java.util.Arrays;
import java.util.Random;

public class PreProcessor extends SimpleDocumentProcessor {


    @Override
    public void process(RawDocument doc) {
        doStuff(doc);
        // this creation of new Document should be part of the doStuff method
        final var newDoc = new RawDocument(doc.content(), new ProcessProperties(randomState()));
        this.documentProcessor.process(newDoc);
    }

    @Override
    public String toString() {
        return "Pre-Processor";
    }

    private ProcessType randomState() {
        final var random = new Random();
        final var randomInt = random.ints(0, 100).findFirst().getAsInt();
        final var randomOrdinal = randomInt % 2 == 0 ? 1 : 0;
        return Arrays.stream(ProcessType.values()).filter(s -> s.ordinal() == randomOrdinal).findFirst().get();
    }
}
