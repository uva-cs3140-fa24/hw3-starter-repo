package edu.virginia.sde.hw3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepresentationTest {

    static final State ohio = new State("Ohio", 1234567890);
    static final State virginia = new State("Virginia", 987654321);

    @Test
    void getRepresentatives_notPresent() {
        Representation rep = new Representation();
        assertEquals(0, rep.getSeats(ohio));
    }

    @Test
    void getRepresentatives_present() {
        Map<State, Integer> initialMap = new HashMap<>(
                Map.of(ohio, 5)
        );
        Representation rep = new Representation(initialMap);
        assertEquals(5, rep.getSeats(ohio));
    }

    @Test
    void setSeats_notPresent() {
        Representation rep = new Representation();
        rep.setSeats(ohio, 5);

        assertEquals(5, rep.getSeats(ohio));
    }

    @Test
    void setSeats_present() {
        Map<State, Integer> initialMap = new HashMap<>(
                Map.of(ohio, 10)
        );
        Representation rep = new Representation(initialMap);
        rep.setSeats(ohio, 5);

        assertEquals(5, rep.getSeats(ohio));
    }

    @Test
    void addRepresentatives_notPresent() {
        Representation rep = new Representation();
        rep.addSeats(ohio, 5);

        assertEquals(5, rep.getSeats(ohio));
    }

    @Test
    void addRepresentatives_present() {
        Map<State, Integer> initialMap = new HashMap<>(
                Map.of(ohio, 10)
        );
        Representation rep = new Representation(initialMap);
        rep.addSeats(ohio, 5);

        assertEquals(15, rep.getSeats(ohio));
    }



    @Test
    void getAllocatedSeats_notEmpty() {
        Map<State, Integer> initialMap = new HashMap<>(
                Map.of(
                        ohio, 10,
                        virginia, 5
                )
        );
        Representation rep = new Representation(initialMap);
        assertEquals(15, rep.getAllocatedSeats());
    }

    @Test
    void getAllocatedSeats_Empty() {
        Representation rep = new Representation();
        assertEquals(0, rep.getAllocatedSeats());
    }
}