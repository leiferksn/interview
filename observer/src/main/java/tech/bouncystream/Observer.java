package tech.bouncystream;

public interface Observer {

    void update();
    void addSubject(Subject s);
}
