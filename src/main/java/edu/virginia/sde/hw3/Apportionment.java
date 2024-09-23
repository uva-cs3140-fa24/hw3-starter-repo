package edu.virginia.sde.hw3;

import java.util.List;

public class Apportionment {
    private final StateSupplier stateSupplier;
    private final ApportionmentMethod apportionmentMethod;
    private final int targetRepresentatives;

    public Apportionment(
            StateSupplier stateSupplier,
            ApportionmentMethod apportionmentMethod,
            int targetRepresentatives
    ) {
        this.stateSupplier = stateSupplier;
        this.apportionmentMethod = apportionmentMethod;
        this.targetRepresentatives = targetRepresentatives;
    }

    public Representation getRepresentation() {
        List<State> states = stateSupplier.getStates();
        return apportionmentMethod.getRepresentation(states, targetRepresentatives);
    }
}
