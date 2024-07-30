package tech.bouncystream;

public class TopicSubscriber implements Observer {

    private String name;
    private Subject topic;

    public TopicSubscriber(String n) {
        this.name = n;
    }

    @Override
    public void update() {
        final String msg = (String) topic.getUpdate(this);
        if (msg == null) {
            System.out.println(this.name + ": No new message!");
        } else {
            System.out.println(this.name + ":: Consuming message :: " + msg ) ;
        }
    }

    @Override
    public void addSubject(Subject s) {
        this.topic = s;
    }
}
