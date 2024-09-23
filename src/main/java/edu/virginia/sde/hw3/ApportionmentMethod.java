package edu.virginia.sde.hw3;

import java.util.List;

public interface ApportionmentMethod {
    public Representation getRepresentation(List<State> stateList, int numRepresentatives);
}
