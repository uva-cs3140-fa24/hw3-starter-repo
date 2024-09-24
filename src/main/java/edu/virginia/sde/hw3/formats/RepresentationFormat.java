package edu.virginia.sde.hw3.formats;

import edu.virginia.sde.hw3.Representation;

/**
 * Describes a particular way to display a representation as a text {@link String}
 */
public interface RepresentationFormat {
    /**
     * Generates a formatted string to display the results of an apportionment
     * @param representation {@link Representation} - the results of an apportionment
     * @return a formatted {@link String}
     */
    String getFormattedString(Representation representation);
}
