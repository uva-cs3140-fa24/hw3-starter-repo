package edu.virginia.sde.hw3.algorithms;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.States;

/**
 * Describes an algorithm for generating an apportionment, an allocation of seats in the US House of Representatives
 * to the states based on population (represented by {@link Representation}.
 */
public interface ApportionmentMethod {
    /**
     * Gets the results of an apportionment for a group of states and a particular target number of representatives
     * @param states the group of {@link States} to allocate representatives to
     * @param numRepresentatives the number of seats in Congress to allocate
     * @return {@link Representation}
     */
    Representation getRepresentation(States states, int numRepresentatives);
}
