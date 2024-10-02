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

package edu.virginia.sde.hw3.algorithms;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;
import edu.virginia.sde.hw3.States;

import java.util.ArrayList;
import java.util.Map.Entry;


/**
 * Represents the apportionment method proposed by Alexander Hamilton, also called
 * <a href="https://en.wikipedia.org/wiki/Quota_method">the quota method</a>
 */
public class HamiltonMethod implements ApportionmentMethod {

    /**
     * Performs an apportionment using the Hamilton method.
     * @param states the group of {@link States} to allocate representatives to
     * @param numRepresentatives the number of seats in Congress to allocate
     * @return {@link Representation}
     */
    @Override
    public Representation getRepresentation(States states, int numRepresentatives) {
        if (states.isEmpty() || states.getTotalPopulation() == 0) {
            throw new IllegalArgumentException("No states provided! Cannot generate Representation map");
        }
        if (numRepresentatives <= 0) {
            throw new IllegalArgumentException("Number of representatives must be greater than zero!");
        }

        //allocate initial representatives
        var divisor = states.getAverageRepresentation(numRepresentatives);
        var initialRepresentation = states.getRoundedDownQuotas(divisor);
        var representation = new Representation(initialRepresentation);


        //allocate "bonus" representatives by largest remainder first
        var remainders = states.getRemainders(divisor);
        var remainingRepresentatives = numRepresentatives - representation.getAllocatedSeats();

        var remainderEntries = new ArrayList<>(remainders.entrySet());
        remainderEntries.sort(Entry.<State, Double>comparingByValue().reversed());

        for (int index = 0; index < remainingRepresentatives; index++) {
            State state = remainderEntries.get(index).getKey();
            representation.addSeats(state, 1);
        }

        return representation;
    }
}
