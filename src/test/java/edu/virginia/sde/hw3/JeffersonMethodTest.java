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

        assertEquals(25, representation.getAllocatedSeats());
        assertEquals(5, representation.size());

        assertEquals(0, representation.getSeats(DE));
        assertEquals(5, representation.getSeats(MD));
        assertEquals(12, representation.getSeats(PA));
        assertEquals(7, representation.getSeats(VA));
        assertEquals(1, representation.getSeats(WV));
    }
}