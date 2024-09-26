package edu.virginia.sde.hw3.formats;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;

import java.util.Comparator;

/**
 * Displays states in alphabetical order by state name
 */
public class AlphabeticalFormat implements RepresentationFormat {
    private final DefaultFormatStringBuilder defaultFormatStringBuilder = new DefaultFormatStringBuilder();

    /**
     * Displays apportionment sorted by state name alphabetically
     * @param representation the mapping of states to number of seats in the House of Representatives
     * @return {@link String} of representatives sorted by state name.
     * @see DefaultFormatStringBuilder#getSortedFormattedString(Representation, Comparator)
     */
    @Override
    public String getFormattedString(Representation representation) {
        Comparator<State> stateComparator = Comparator.comparing(state -> state.name().toUpperCase());
        return defaultFormatStringBuilder.getSortedFormattedString(representation, stateComparator);
    }

}
