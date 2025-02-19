package tech.bouncystream;


public class XLSXDocumentProcessingApp {

    public static void main(String[] args) {

        final var headersFromColsProcessor = new HeadersFromColsProcessor();
        final var headersFromRowsProcessor = new HeadersFromRowsProcessor();

        final var mainProcessor = new MainProcessor();
        mainProcessor.addProcessor(headersFromColsProcessor);
        mainProcessor.addProcessor(headersFromRowsProcessor);

        final var preProcessor = new PreProcessor();
        preProcessor.setContainer(mainProcessor);

        final var writeProcessor = new WriteProcessor();

        headersFromColsProcessor.addProcessor(writeProcessor);
        headersFromRowsProcessor.addProcessor(writeProcessor);

        preProcessor.process(new Document(new Content(), new Properties(0)));



    }

}