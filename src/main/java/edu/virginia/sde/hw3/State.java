package edu.virginia.sde.hw3;

/**
 * Represents a state
 * @param name The name of the state
 * @param population The number of residents in the state for apportionment purposes
 */
public record State(
        String name,
        int population
) {
}
