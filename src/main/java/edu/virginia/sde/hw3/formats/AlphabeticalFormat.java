/**
 * Starter code copyright Paul "Will" McBurney, 2024 - starter code visible at:
 * <a href="https://github.com/uva-cs3140-fa24/hw3-starter-repo">https://github.com/uva-cs3140-fa24/hw3-starter-repo</a>
 * <br><br>
 * This code may not be reposted, redistributed, or shared with others without explicit written consent
 * by Paul "Will" McBurney. This code may not be used by students in portfolios or job interviews.
 * <br><br>
 * For students, this code is considered protected by the University of Virginia Academic Honesty policy,
 * and sharing this code with anyone without explicit permission will be treated as an academic honesty
 * violation.
 * <br><br>
 * To other professors, if interested in sharing reusing this assignment, please contact Prof. McBurney via email.
 */

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
