package edu.virginia.sde.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JeffersonMethod implements ApportionmentMethod {

    @Override
    public Representation getRepresentation(List<State> stateList, int numRepresentatives) {
        if (stateList.isEmpty()) {
            throw new IllegalArgumentException("No states provided! Cannot generate Representation map");
        }
        if (numRepresentatives <= 0) {
            throw new IllegalArgumentException("Number of representatives must be greater than zero!");
        }

        double highDivisor = Quotas.getInitialDivisor(stateList, numRepresentatives);
        double lowDivisor = 1;

        while (highDivisor != lowDivisor) {
            double midDivisor = (highDivisor + lowDivisor) / 2;
            Map<State, Integer> roundedDownQuotas = Quotas.getRoundedDownQuotas(stateList, midDivisor);
            Representation representation = new Representation(roundedDownQuotas);
            if (representation.getTotalRepresentatives() == numRepresentatives) {
                return representation;
            } else if (representation.getTotalRepresentatives() > numRepresentatives) {
                lowDivisor = midDivisor;
            } else if (representation.getTotalRepresentatives() < numRepresentatives) {
                highDivisor = midDivisor;
            }
        }
        throw new IllegalArgumentException("Unsolvable Apportionment with the given parameters");
    }
}
