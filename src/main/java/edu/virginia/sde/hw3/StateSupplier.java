package edu.virginia.sde.hw3;

/**
 * Describes a means of retrieving the input state name and population data.
 */
public interface StateSupplier {
    /**
     * Retrives a group of {@link State}s from a data source
     * @return a group of {@link States} from the data source
     */
    States getStates();
}
