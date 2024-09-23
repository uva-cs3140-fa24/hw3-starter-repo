package edu.virginia.sde.hw3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HamiltonMethodTest {



    /**
     * Example from HW1 Part1 Appendix <br>
     * <a href="https://docs.google.com/document/d/1vs9rsE9rbOP590e01oonExrxHlbil4XwrtE7sG1fjM0/edit#heading=h.cankebxhr2i0">
     *     Link to document</a>
     */

    @Test
    void getHamiltonRepresentation() {
        State DE = new State("Delaware", 989948);
        State MD = new State("Maryland", 6177224);
        State PA = new State("Pennsylvania", 13002700);
        State VA = new State("Virginia", 8631393);
        State WV = new State("West Virginia", 1793716);

        List<State> stateList = List.of(DE, MD, PA, VA, WV);
        HamiltonMethod hamiltonMethod = new HamiltonMethod();
        Representation representation = hamiltonMethod.getRepresentation(stateList, 25);

        assertEquals(25, representation.getTotalRepresentatives());
        assertEquals(5, representation.size());

        assertEquals(1, representation.getRepresentatives(DE));
        assertEquals(5, representation.getRepresentatives(MD));
        assertEquals(11, representation.getRepresentatives(PA));
        assertEquals(7, representation.getRepresentatives(VA));
        assertEquals(1, representation.getRepresentatives(WV));
    }
}