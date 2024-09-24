package edu.virginia.sde.hw3;

import java.util.List;

/**
 * This class processes the Apportionment by getting the list of states from the {@link StateSupplier} and calling the
 * {@link ApportionmentMethod} to get the {@link Representation}.
 */
public class Apportionment {
    /** The data source for state information */
    private final StateSupplier stateSupplier;

    /** The apportionment algorithm to use */
    private final ApportionmentMethod apportionmentMethod;

    /** The number of representatives to allocate */
    private final int targetRepresentatives;

    /**
     * Creates an Apportionment object
     * @param stateSupplier the data source for state information
     * @param apportionmentMethod the apportionment algorithm to use
     * @param targetRepresentatives the number of representatives to allocate
     */
    public Apportionment(
            StateSupplier stateSupplier,
            ApportionmentMethod apportionmentMethod,
            int targetRepresentatives
    ) {
        this.stateSupplier = stateSupplier;
        this.apportionmentMethod = apportionmentMethod;
        this.targetRepresentatives = targetRepresentatives;
    }

    /**
     * Get the results of the apportionment
     * @return {@link Representation}
     */
    public Representation getRepresentation() {
        List<State> states = stateSupplier.getStates();
        return apportionmentMethod.getRepresentation(states, targetRepresentatives);
    }
}
