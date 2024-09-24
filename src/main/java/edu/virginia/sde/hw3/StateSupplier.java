package edu.virginia.sde.hw3;

import java.util.List;

/**
 * Describes a means of retrieving the input state name and population data.
 */
public interface StateSupplier {
    /**
     * Retrives a list of states from a data source
     * @return a {@link List} of {@link State} objects
     */
    List<State> getStates();
}
