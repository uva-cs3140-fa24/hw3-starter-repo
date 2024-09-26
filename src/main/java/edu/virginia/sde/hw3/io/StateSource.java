package edu.virginia.sde.hw3.io;

import edu.virginia.sde.hw3.State;
import edu.virginia.sde.hw3.States;

/**
 * Describes a means of retrieving the input state name and population data.
 */
public interface StateSource {
    /**
     * Retrives a group of {@link State}s from a data source
     * @return a group of {@link States} from the data source
     */
    States getStates();
}
