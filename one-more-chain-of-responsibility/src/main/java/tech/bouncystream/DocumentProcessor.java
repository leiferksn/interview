package tech.bouncystream;

public interface DocumentProcessor {

    Document process(Document doc);
    default void doStuff() {
         System.out.println("Processing stuff with " + this);
    }
}
