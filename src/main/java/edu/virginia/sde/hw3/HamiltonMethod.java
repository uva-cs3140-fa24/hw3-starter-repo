package edu.virginia.sde.hw3;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;


/**
 * Represents the apportionment method proposed by Alexander Hamilton, also called
 * <a href="https://en.wikipedia.org/wiki/Quota_method">the quota method</a>
 */
public class HamiltonMethod implements ApportionmentMethod {

    /**
     * Performs an apportionment using the Hamilton method.
     * @param stateList the {@link List} of {@link State}s to apportion
     * @param numRepresentatives the number of seats in Congress to allocate
     * @return {@link Representation}
     */
    @Override
    public Representation getRepresentation(List<State> stateList, int numRepresentatives) {
        if (stateList.isEmpty()) {
            throw new IllegalArgumentException("No states provided! Cannot generate Representation map");
        }
        if (numRepresentatives <= 0) {
            throw new IllegalArgumentException("Number of representatives must be greater than zero!");
        }

        var divisor = Quotas.getAverageRepresentation(stateList, numRepresentatives);
        var initialRepresentation = Quotas.getRoundedDownQuotas(stateList, divisor);
        var representation = new Representation(initialRepresentation);

        var remainders = Quotas.getRemainders(stateList, divisor);
        var remainingRepresentatives = numRepresentatives - representation.getAllocatedSeats();
        remainders.entrySet().stream()
                .sorted(Comparator.comparing(Entry<State, Double>::getValue).reversed())
                .limit(remainingRepresentatives)
                .forEach(entry -> representation.addSeats(entry.getKey(), 1));

        return representation;
    }
}
