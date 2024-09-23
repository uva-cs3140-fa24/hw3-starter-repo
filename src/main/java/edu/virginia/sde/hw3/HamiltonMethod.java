package edu.virginia.sde.hw3;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public class HamiltonMethod implements ApportionmentMethod {

    @Override
    public Representation getRepresentation(List<State> stateList, int numRepresentatives) {
        if (stateList.isEmpty()) {
            throw new IllegalArgumentException("No states provided! Cannot generate Representation map");
        }
        if (numRepresentatives <= 0) {
            throw new IllegalArgumentException("Number of representatives must be greater than zero!");
        }

        var divisor = Quotas.getInitialDivisor(stateList, numRepresentatives);
        var initialRepresentation = Quotas.getRoundedDownQuotas(stateList, divisor);
        var representation = new Representation(initialRepresentation);

        var remainders = Quotas.getRemainders(stateList, divisor);
        var remainingRepresentatives = numRepresentatives - representation.getTotalRepresentatives();
        remainders.entrySet().stream()
                .sorted(Comparator.comparing(Entry<State, Double>::getValue).reversed())
                .limit(remainingRepresentatives)
                .forEach(entry -> representation.addRepresentatives(entry.getKey(), 1));

        return representation;
    }
}
