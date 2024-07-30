package tech.bouncystream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Box<T extends Object> {

    private  List<T> items;

    public Box(T t) {
        this.items = new ArrayList<>();
        this.items.add(t);
    }

    public Optional<T> outOfTheBox(T x) {
        return items.stream().filter(i -> i.equals(x)).findFirst();
    }

}
