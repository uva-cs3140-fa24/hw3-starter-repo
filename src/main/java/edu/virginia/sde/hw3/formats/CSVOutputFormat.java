package edu.virginia.sde.hw3.formats;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Generates the text for outputting the result of an apportionment ({@link Representation}) to a CSV file
 * sorted alphabetically.
 */
public class CSVOutputFormat implements RepresentationFormat{

    @Override
    public String getFormattedString(Representation representation) {
        List<State> sortedStates = new ArrayList<>(representation.getStates());
        sortedStates.sort(Comparator.comparing(state -> state.name()));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("State,Population\n");
        for (State state: sortedStates) {
            stringBuilder.append(
                    String.format("%s,%d\n", state.name(), representation.getSeats(state))
            );
        }
        return stringBuilder.toString();
    }
}
