package tech.bouncystream;

public record RawDocument(Content content, ProcessProperties processProperties) {

    @Override
    public String toString() {
        return "of type " + (processProperties.processType() != null ? processProperties.processType().toString() : "undefined");
    }
}
