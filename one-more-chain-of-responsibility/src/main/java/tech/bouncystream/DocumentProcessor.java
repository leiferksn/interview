package tech.bouncystream;

public interface DocumentProcessor {

    RawDocument process(RawDocument doc);
    default void doStuff() {
         System.out.println("Processing stuff with " + this);
    }
}
