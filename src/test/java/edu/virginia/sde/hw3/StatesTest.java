package edu.virginia.sde.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatesTest {

    static final State OH = new State("Ohio", 300);
    static final State VA = new State("Virginia", 150);
    static final State DE = new State("Delaware", 50);

    private States states;

    @BeforeEach
    void setUp() {
        List<State> initialStateList = new ArrayList<>(List.of(OH, VA, DE));
        states = new States(initialStateList);
    }

    @Test
    void getTotalPopulation() {
        assertEquals(500, states.getTotalPopulation());
    }

    @Test
    void getAverageRepresentation_evenlyDivisible() {
        assertEquals(50.0, states.getAverageRepresentation(10), 1e-14);
    }

    @Test
    void getAverageRepresentation_notDivisible() {
        assertEquals(71.4285714, states.getAverageRepresentation(7), 1e-7);
    }

    @Test
    void getQuotas_evenlyDivisible() {
        var quotas = states.getQuotas(50);
        assertEquals(3, quotas.size());
        assertEquals(6.0, quotas.get(OH), 1e-14);
        assertEquals(3.0, quotas.get(VA), 1e-14);
        assertEquals(1.0, quotas.get(DE), 1e-14);
    }

    @Test
    void getQuotas_notDivisible() {
        var quotas = states.getQuotas(71.4285714);
        assertEquals(3, quotas.size());
        assertEquals(4.2, quotas.get(OH), 1e-7);
        assertEquals(2.1, quotas.get(VA), 1e-7);
        assertEquals(0.7, quotas.get(DE), 1e-7);
    }

    @Test
    void getRoundedDownQuotas_evenlyDivisible() {
        var roundedDownQuotas = states.getRoundedDownQuotas(50);
        assertEquals(3, roundedDownQuotas.size());
        assertEquals(6, roundedDownQuotas.get(OH));
        assertEquals(3, roundedDownQuotas.get(VA));
        assertEquals(1, roundedDownQuotas.get(DE));
    }

    @Test
    void getRoundedDownQuotas_notDivisible() {
        var roundedDownQuotas = states.getRoundedDownQuotas(71.4285714);
        assertEquals(3, roundedDownQuotas.size());
        assertEquals(4, roundedDownQuotas.get(OH));
        assertEquals(2, roundedDownQuotas.get(VA));
        assertEquals(0, roundedDownQuotas.get(DE));
    }

    @Test
    void getRemainders_evenlyDivisible() {
        var remainders = states.getRemainders(50);
        assertEquals(3, remainders.size());
        assertEquals(0, remainders.get(OH));
        assertEquals(0, remainders.get(VA));
        assertEquals(0, remainders.get(DE));
    }

    @Test
    void getRemainders_notDivisible() {
        var remainders = states.getRemainders(71.4285714);
        assertEquals(3, remainders.size());
        assertEquals(0.2, remainders.get(OH), 1e-7);
        assertEquals(0.1, remainders.get(VA), 1e-7);
        assertEquals(0.7, remainders.get(DE), 1e-7);
    }
}