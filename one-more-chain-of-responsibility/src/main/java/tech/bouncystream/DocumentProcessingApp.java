package tech.bouncystream;


import static tech.bouncystream.ProcessType.HEADERS_FROM_COLS;
import static tech.bouncystream.ProcessType.HEADERS_FROM_ROWS;

public class DocumentProcessingApp {

    public static void main(String[] args) {

        final var writeProcessor = new WriteProcessor();
        writeProcessor.nextProcessor(null);

        final var headersFromColsProcessor = new HeadersFromColumnsProcessor();
        headersFromColsProcessor.nextProcessor(writeProcessor);

        final var headersFromRowsProcessor = new HeadersFromRowsProcessor();
        headersFromRowsProcessor.nextProcessor(writeProcessor);

        final var preProcessor = new PreProcessor();
        final var mainProcessor = new MainProcessor();
        mainProcessor.addProcessor(HEADERS_FROM_COLS, headersFromColsProcessor);
        mainProcessor.addProcessor(HEADERS_FROM_ROWS, headersFromRowsProcessor);

        preProcessor.nextProcessor(mainProcessor);


        preProcessor.process(new RawDocument(new Content(), new ProcessProperties(null)));

    }

}