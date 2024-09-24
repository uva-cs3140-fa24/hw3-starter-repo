package edu.virginia.sde.hw3;

import java.io.IOException;

/**
 * Describes an output source to send the results of the apportionment to
 */
public interface OutputSource {
    /**
     * Writes the results of the apportionment algorithm to some output source
     * @param representation {@link Representation} the results of an apportionment algorithm
     * @throws IOException if there is any error in writing to the output source
     */
    void writeToOutput(Representation representation) throws IOException;
}
