package edu.virginia.sde.hw3;

public class DuplicateStateNameException extends RuntimeException {
    /** The state added which caused the exception */
    private final State addedState;

    /** The set of states when the execption was thrown */
    private final States states;

    public DuplicateStateNameException(State addedState, States states) {
        super(generateErrorMessage(states, addedState));
        this.addedState = addedState;
        this.states = states;
    }

    private static String generateErrorMessage(States states, State addedState) {
        return String.format("Attempted to add duplicate state name \"%s\" to States set of size %d", addedState.name(), states.size());
    }

    public States getStates() {
        return states;
    }

    public State getAddedState() {
        return addedState;
    }
}
