package edu.virginia.sde.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DefaultFormatStringBuilder {
    String getSortedString(Representation representation, Comparator<State> stateComparator) {
        List<State> states = new ArrayList<>(representation.getStates());

        states.sort(stateComparator);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%-15s|%20s|%5s\n".formatted("State", "Population", "Reps"));
        for (State state : states) {
            stringBuilder.append(String.format("%-15s|%20s|%5s\n",
                    state.name(),
                    state.population(),
                    representation.getRepresentatives(state)));
        }
        return stringBuilder.toString();
    }
}