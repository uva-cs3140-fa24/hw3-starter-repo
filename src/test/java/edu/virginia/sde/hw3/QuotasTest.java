package edu.virginia.sde.hw3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuotasTest {

    static final State OH = new State("Ohio", 300);
    static final State VA = new State("Virginia", 150);
    static final State DE = new State("Delaware", 50);

    static final List<State> stateList = List.of(OH, VA, DE);

    @Test
    void getTotalPopulation() {
        assertEquals(500, Quotas.getTotalPopulation(stateList));
    }

    @Test
    void getInitialDivisor_evenlyDivisible() {
        assertEquals(50.0, Quotas.getInitialDivisor(stateList, 10), 1e-14);
    }

    @Test
    void getInitialDivisor_notDivisible() {
        assertEquals(71.4285714, Quotas.getInitialDivisor(stateList, 7), 1e-7);
    }

    @Test
    void getQuotas_evenlyDivisible() {
        var quotas = Quotas.getQuotas(stateList, 50);
        assertEquals(3, quotas.size());
        assertEquals(6.0, quotas.get(OH), 1e-14);
        assertEquals(3.0, quotas.get(VA), 1e-14);
        assertEquals(1.0, quotas.get(DE), 1e-14);
    }

    @Test
    void getQuotas_notDivisible() {
        var quotas = Quotas.getQuotas(stateList, 71.4285714);
        assertEquals(3, quotas.size());
        assertEquals(4.2, quotas.get(OH), 1e-7);
        assertEquals(2.1, quotas.get(VA), 1e-7);
        assertEquals(0.7, quotas.get(DE), 1e-7);
    }

    @Test
    void getRoundedDownQuotas_evenlyDivisible() {
        var roundedDownQuotas = Quotas.getRoundedDownQuotas(stateList, 50);
        assertEquals(3, roundedDownQuotas.size());
        assertEquals(6, roundedDownQuotas.get(OH));
        assertEquals(3, roundedDownQuotas.get(VA));
        assertEquals(1, roundedDownQuotas.get(DE));
    }

    @Test
    void getRoundedDownQuotas_notDivisible() {
        var roundedDownQuotas = Quotas.getRoundedDownQuotas(stateList, 71.4285714);
        assertEquals(3, roundedDownQuotas.size());
        assertEquals(4, roundedDownQuotas.get(OH));
        assertEquals(2, roundedDownQuotas.get(VA));
        assertEquals(0, roundedDownQuotas.get(DE));
    }

    @Test
    void getRemainders_evenlyDivisible() {
        var remainders = Quotas.getRemainders(stateList, 50);
        assertEquals(3, remainders.size());
        assertEquals(0, remainders.get(OH));
        assertEquals(0, remainders.get(VA));
        assertEquals(0, remainders.get(DE));
    }

    @Test
    void getRemainders_notDivisible() {
        var remainders = Quotas.getRemainders(stateList, 71.4285714);
        assertEquals(3, remainders.size());
        assertEquals(0.2, remainders.get(OH), 1e-7);
        assertEquals(0.1, remainders.get(VA), 1e-7);
        assertEquals(0.7, remainders.get(DE), 1e-7);
    }
}