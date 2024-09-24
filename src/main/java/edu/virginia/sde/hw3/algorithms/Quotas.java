package edu.virginia.sde.hw3.algorithms;

import edu.virginia.sde.hw3.State;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A library of mathematical functions used in various apportionment algorithms.
 */
public class Quotas {
    /**
     * Returns the total population of a list of states
     * @param stateList a {@link List} of {@link State}s
     * @return the combined population of every state
     */
    public static int getTotalPopulation(List<State> stateList) {
        int sum = 0;
        for (State state : stateList) {
            int population = state.population();
            sum += population;
        }
        return sum;
    }

    /**
     * Returns the average population per seat in US House of Representatives
     * @param stateList a {@link List} of {@link State}s
     * @param numberOfSeats the total number of seats in the House of Representatives
     * @return the average number of residents per seat for all states combined
     */
    public static double getAverageRepresentation(List<State> stateList, int numberOfSeats) {
        return (double) getTotalPopulation(stateList) / numberOfSeats;
    }

    /**
     * Get the quota for each state, or the expected number of seats allocated to the state based on
     * a particular divisor.
     * @param stateList a {@link List} of {@link State}s
     * @param divisor the population per seat
     * @return A mapping of states to their floating-point quotas
     */
    public static Map<State, Double> getQuotas(List<State> stateList, double divisor) {
        Map<State, Double> quotas = new HashMap<>();
        for (State state : stateList) {
            quotas.put(state, state.population() / divisor);
        }
        return quotas;
    }

    /**
     * Does the same thing as {@link Quotas#getQuotas(List, double)} but rounds down the result to an integer
     * @param stateList a {@link List} of {@link State}s
     * @param divisor divisor the population per seat
     * @return A mapping of states to their floating-point quotas always rounded *down*. So a quota of 4.99 would be
     * set equal to 4
     */
    public static Map<State, Integer> getRoundedDownQuotas(List<State> stateList, double divisor) {
        Map<State, Double> quotas = getQuotas(stateList, divisor);
        Map<State, Integer> roundedDownQuotas = new HashMap<>();
        for (State state : quotas.keySet()) {
            roundedDownQuotas.put(state, (int) Math.floor(quotas.get(state)));
        }
        return roundedDownQuotas;
    }

    /**
     * Get the remainders of the quotes from {@link Quotas#getQuotas(List, double)}
     * @param stateList a {@link List} of {@link State}s
     * @param divisor divisor the population per seat
     * @return A mapping of states to their floating point remainders. For example, if a state has s quota of 4.73, then
     * that state maps to 0.73 in this output.
     */
    public static Map<State, Double> getRemainders(List<State> stateList, double divisor) {
        Map<State, Double> quotas = getQuotas(stateList, divisor);
        Map<State, Double> remainders = new HashMap<>();
        for (State state : quotas.keySet()) {
            remainders.put(state,  quotas.get(state) - Math.floor(quotas.get(state)));
        }
        return remainders;
    }
}
