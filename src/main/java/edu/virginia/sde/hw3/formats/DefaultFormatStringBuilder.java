package edu.virginia.sde.hw3.formats;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represent a Default format for displaying an apportionment result to console. This format will display as:<br>
 * <pre>
 * State          |          Population| Reps <br>
 * Alabama        |             5030053|    7 <br>
 * Alaska         |              736081|    1 <br>
 * Arizona        |             7158923|    9 <br>
 * </pre>
 *
 * Sorted by a specific {@link Comparator}
 */
public class DefaultFormatStringBuilder {
    /**
     * Generates a table-link string for displaying the results an apportionment console. <br>
     *  <pre>
     *  State          |          Population| Reps <br>
     *  Alabama        |             5030053|    7 <br>
     *  Alaska         |              736081|    1 <br>
     *  Arizona        |             7158923|    9 <br>
     *  </pre>
     * @param representation the results of an apportionment to display
     * @param stateComparator how the states are sorted in the table
     * @return a formatted {@link String}
     */
    String getSortedFormattedString(Representation representation, Comparator<State> stateComparator) {
        List<State> states = new ArrayList<>(representation.getStates());

        states.sort(stateComparator);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%-15s|%19s |%5s\n".formatted("State", "Population", "Reps"));
        for (State state : states) {
            stringBuilder.append(String.format("%-15s|%19s |%5s\n",
                    state.name(),
                    state.population(),
                    representation.getSeats(state)));
        }
        return stringBuilder.toString();
    }
}