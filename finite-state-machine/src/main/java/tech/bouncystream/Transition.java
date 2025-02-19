package tech.bouncystream;

public record Transition(State fromState, State toState, StuffToDo stuffToDo) {

    void doStuff() {
        System.out.println("DO: " + stuffToDo);
    }

}
