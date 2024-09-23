package edu.virginia.sde.hw3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JeffersonMethodTest {

    @Test
    void getRepresentation() {
        State DE = new State("Delaware", 989948);
        State MD = new State("Maryland", 6177224);
        State PA = new State("Pennsylvania", 13002700);
        State VA = new State("Virginia", 8631393);
        State WV = new State("West Virginia", 1793716);

        List<State> stateList = List.of(DE, MD, PA, VA, WV);
        JeffersonMethod jeffersonMethod = new JeffersonMethod();
        Representation representation = jeffersonMethod.getRepresentation(stateList, 25);

        assertEquals(25, representation.getTotalRepresentatives());
        assertEquals(5, representation.size());

        assertEquals(0, representation.getRepresentatives(DE));
        assertEquals(5, representation.getRepresentatives(MD));
        assertEquals(12, representation.getRepresentatives(PA));
        assertEquals(7, representation.getRepresentatives(VA));
        assertEquals(1, representation.getRepresentatives(WV));
    }
}