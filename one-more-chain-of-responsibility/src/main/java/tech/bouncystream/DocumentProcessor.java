package tech.bouncystream;

public interface DocumentProcessor {

    void process(RawDocument doc);

    default void doStuff(RawDocument document) {
         System.out.println("Processing document " + document +  " with " + this);
    }
}