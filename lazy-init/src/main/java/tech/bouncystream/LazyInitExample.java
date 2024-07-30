package tech.bouncystream;

import java.util.function.Supplier;

public class LazyInitExample {
    public static void main(String[] args) {
        final Supplier<LazyObject> lazyObjectSupplier = () -> new LazyObject();
        System.out.println(lazyObjectSupplier.get().name());
    }
}

class LazyObject {

    private String name;

    public  LazyObject() {
        this.name = "Lazy Object";
    }

    public String name() {
        return """
                My name is: %s
                """.formatted(this.name);
    }

}


