package tech.bouncystream;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {
    private Map<State, Transition> transitions = new HashMap<>();
    private Document document;

    public StateMachine() {
        this.document = new Document();
        this.document.changeState(State.READY_TO_PROCESS);
    }

    public void handleDocument(){
        final var transition = transitions.get(this.document.state());
        transition.doStuff();
        this.document.changeState(transition.toState());
    }

    public void addTransition(State fromState, State toState, StuffToDo whatToDo) {
        final var transition = new Transition(fromState, toState, whatToDo);
        this.transitions.put(fromState, transition);
    }

    public static void main(final String args[]) {
        final var stateMachine = new StateMachine();
        stateMachine.addTransition(State.READY_TO_PROCESS, State.IN_PRE_PROCESS, StuffToDo.PREPARE);
        stateMachine.addTransition(State.IN_PRE_PROCESS, State.IN_PROCESS, StuffToDo.PRE_PROCESS);
        stateMachine.addTransition(State.IN_PROCESS, State.IN_WRITE, StuffToDo.PROCESS);
        stateMachine.addTransition(State.IN_WRITE, State.DONE, StuffToDo.WRITE);

        stateMachine.handleDocument();
        stateMachine.handleDocument();

        // etc.
    }
}