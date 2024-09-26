package edu.virginia.sde.hw3;

import edu.virginia.sde.hw3.algorithms.ApportionmentMethod;
import edu.virginia.sde.hw3.algorithms.UnsolvableApportionmentException;
import edu.virginia.sde.hw3.io.StateSource;

/**
 * This class processes the Apportionment by getting the list of states from the {@link StateSource} and calling the
 * {@link ApportionmentMethod} to get the {@link Representation}.
 */
public class Apportionment {
    /** The data source for state information */
    private final StateSource stateSource;

    /** The apportionment algorithm to use */
    private final ApportionmentMethod apportionmentMethod;

    /** The number of representatives to allocate */
    private final int targetRepresentatives;

    /**
     * Creates an Apportionment object
     * @param stateSource the data source for state information
     * @param apportionmentMethod the apportionment algorithm to use
     * @param targetRepresentatives the number of representatives to allocate
     */
    public Apportionment(
            StateSource stateSource,
            ApportionmentMethod apportionmentMethod,
            int targetRepresentatives
    ) {
        this.stateSource = stateSource;
        this.apportionmentMethod = apportionmentMethod;
        this.targetRepresentatives = targetRepresentatives;
    }

    /**
     * Get the results of the apportionment
     * @return {@link Representation}
     */
    public Representation getRepresentation() {
        States states = stateSource.getStates();
        if (states.isEmpty() || states.getTotalPopulation() <= 0) {
            String errorMessage = "Cannot apportion representatives as no states with a positive population were provided";
            throw new UnsolvableApportionmentException(errorMessage);
        }

        return apportionmentMethod.getRepresentation(states, targetRepresentatives);
    }
}
