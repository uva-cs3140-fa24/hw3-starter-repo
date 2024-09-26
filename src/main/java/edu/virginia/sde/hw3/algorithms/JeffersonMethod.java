package edu.virginia.sde.hw3.algorithms;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;
import edu.virginia.sde.hw3.States;

import java.util.Map;

/**
 * Represents the apportionment method proposed by Thomas Jefferson, adopted in the 1792 and used until 1842, when
 * the Webster method was adopted. Also called <a href="https://en.wikipedia.org/wiki/D%27Hondt_method">the
 * D'Hondt method</a>
 */
public class JeffersonMethod implements ApportionmentMethod {

    /**
     * Gets the apportionment generated by the Jefferson algorithm. Uses a binary search to find the
     * appropriate divisor.
     * @param states the group of {@link States} to allocate representatives to.
     * @param numRepresentatives the number of seats in Congress to allocate
     * @return {@link Representation}
     * @throws IllegalArgumentException if it is not possible to apportion the given number of representatives
     * mathematically (often due to ties between states whose populations have common factors)
     */
    @Override
    public Representation getRepresentation(States states, int numRepresentatives) {
        if (states.isEmpty() || states.getTotalPopulation() == 0) {
            throw new IllegalArgumentException("No states provided! Cannot generate Representation map");
        }
        if (numRepresentatives <= 0) {
            throw new IllegalArgumentException("Number of representatives must be greater than zero!");
        }

        double highDivisor = states.getAverageRepresentation(numRepresentatives);
        double lowDivisor = 1;

        while (highDivisor != lowDivisor) {
            double midDivisor = (highDivisor + lowDivisor) / 2;
            Map<State, Integer> roundedDownQuotas = states.getRoundedDownQuotas(midDivisor);
            Representation representation = new Representation(roundedDownQuotas);

            if (representation.getAllocatedSeats() > numRepresentatives) {
                lowDivisor = midDivisor;
            } else if (representation.getAllocatedSeats() < numRepresentatives) {
                highDivisor = midDivisor;
            } else {
                return representation;
            }
        }
        throw new UnsolvableApportionmentException("Unsolvable Apportionment with the given parameters");
    }
}
