package edu.virginia.sde.hw3;

/**
 * Thrown when a state with a duplicate name is added to a {@link States} collection
 */
public class DuplicateStateNameException extends RuntimeException {
    /** The state added which caused the exception */
    private final State addedState;

    /** The set of states when the execption was thrown */
    private final States states;

    /**
     * Constructs a DuplicateStateNameException with the specified state and states collection.
     *
     * @param addedState the state that caused the exception due to its duplicate name
     * @param states the collection of states that already contains a state with the same name
     */
    public DuplicateStateNameException(State addedState, States states) {
        super(generateErrorMessage(states, addedState));
        this.addedState = addedState;
        this.states = states;
    }

    private static String generateErrorMessage(States states, State addedState) {
        return String.format("Attempted to add duplicate state name \"%s\" to States set of size %d", addedState.name(), states.size());
    }

    /**
     * Get the state that was added whose name caused the exception
     * @return the {@link State} whose name was already present in the States collection
     */
    public State getAddedState() {
        return addedState;
    }

    /**
     * Get the current collection of states that was added to
     * @return the {@link States} collection
     */
    public States getStates() {
        return states;
    }
}
