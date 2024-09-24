package edu.virginia.sde.hw3.algorithms;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;

import java.util.List;

/**
 * Describes an algorithm for generating an apportionment, an allocation of seats in the US House of Representatives
 * to the states based on population (represented by {@link Representation}.
 */
public interface ApportionmentMethod {
    /**
     * Gets the results of an apportionment
     * @param stateList the {@link List} of {@link State}s to apportion
     * @param numRepresentatives the number of seats in Congress to allocate
     * @return {@link Representation}
     */
    Representation getRepresentation(List<State> stateList, int numRepresentatives);
}
