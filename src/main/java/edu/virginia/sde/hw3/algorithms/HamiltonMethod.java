package edu.virginia.sde.hw3.algorithms;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;
import edu.virginia.sde.hw3.States;

import java.util.Comparator;
import java.util.Map.Entry;


/**
 * Represents the apportionment method proposed by Alexander Hamilton, also called
 * <a href="https://en.wikipedia.org/wiki/Quota_method">the quota method</a>
 */
public class HamiltonMethod implements ApportionmentMethod {

    /**
     * Performs an apportionment using the Hamilton method.
     * @param states the group of {@link States} to allocate representatives to
     * @param numRepresentatives the number of seats in Congress to allocate
     * @return {@link Representation}
     */
    @Override
    public Representation getRepresentation(States states, int numRepresentatives) {
        if (states.isEmpty()) {
            throw new IllegalArgumentException("No states provided! Cannot generate Representation map");
        }
        if (numRepresentatives <= 0) {
            throw new IllegalArgumentException("Number of representatives must be greater than zero!");
        }

        var divisor = states.getAverageRepresentation(numRepresentatives);
        var initialRepresentation = states.getRoundedDownQuotas(divisor);
        var representation = new Representation(initialRepresentation);

        var remainders = states.getRemainders(divisor);
        var remainingRepresentatives = numRepresentatives - representation.getAllocatedSeats();
        remainders.entrySet().stream()
                .sorted(Comparator.comparing(Entry<State, Double>::getValue).reversed())
                .limit(remainingRepresentatives)
                .forEach(entry -> representation.addSeats(entry.getKey(), 1));

        return representation;
    }
}
