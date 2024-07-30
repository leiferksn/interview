package tech.bouncystream;

public class GenericsExample {

    public static void main(String[] args) {

        final var doubles = new Box<String>("2.0");
        System.out.println("TWO?: " + doubles.outOfTheBox("2.0").orElse("-1.0"));
        System.out.println("TWO?: " + doubles.outOfTheBox("3.0").orElse("-1.0"));
    }

}

