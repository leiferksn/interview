package tech.bouncystream;

import java.util.ArrayList;
import java.util.List;

public class Topic implements Subject {

    private List<Observer> observers;
    private String msg;

    private boolean changed;
    private final Object MUTEX = new Object();

    public Topic() {
        this.observers = new ArrayList<>();
    }


    @Override
    public void register(Observer o) {
        synchronized (MUTEX) {
            if (!observers.contains(o)) {
                observers.add(o);
            }
        }
    }

    @Override
    public void unregister(Observer o) {
        synchronized (MUTEX) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        synchronized (MUTEX) {
            if (!changed) {
                return;
            }
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }

        for (Observer o: observersLocal) {
            o.update();
        }

    }

    @Override
    public Object getUpdate(Observer o) {
        return this.msg;
    }

    public void postMessage(String msg) {
        System.out.println("Message posted to topic: " + msg);
        this.msg = msg;
        this.changed = true;
        notifyObservers();
    }
}
