package tech.bouncystream;

import java.util.List;

public interface Subject {

    void register(Observer o);
    void unregister(Observer o);

    void notifyObservers();
    Object getUpdate(Observer o);

}
