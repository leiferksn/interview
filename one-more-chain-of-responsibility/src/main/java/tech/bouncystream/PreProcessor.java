package tech.bouncystream;

import java.util.Arrays;
import java.util.Random;

public class PreProcessor extends SimpleDocumentProcessor {


    @Override
    public Document process(Document doc) {
        doStuff();
        // this creation of new Document should be part of the doStuff method
        final var newDoc = new Document(doc.content(), new Properties(randomState()));
        this.documentProcessor.process(newDoc);
        return doc;
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
