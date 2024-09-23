package edu.virginia.sde.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quotas {
    public static int getTotalPopulation(List<State> stateList) {
        int sum = 0;
        for (State state : stateList) {
            int population = state.population();
            sum += population;
        }
        return sum;
    }

    public static double getInitialDivisor(List<State> stateList, int numRepresentatives) {
        return (double) getTotalPopulation(stateList) / numRepresentatives;
    }

    public static Map<State, Double> getQuotas(List<State> stateList, double divisor) {
        Map<State, Double> quotas = new HashMap<>();
        for (State state : stateList) {
            quotas.put(state, state.population() / divisor);
        }
        return quotas;
    }

    public static Map<State, Integer> getRoundedDownQuotas(List<State> stateList, double divisor) {
        Map<State, Double> quotas = getQuotas(stateList, divisor);
        Map<State, Integer> roundedDownQuotas = new HashMap<>();
        for (State state : quotas.keySet()) {
            roundedDownQuotas.put(state, (int) Math.floor(quotas.get(state)));
        }
        return roundedDownQuotas;
    }

    public static Map<State, Double> getRemainders(List<State> stateList, double divisor) {
        Map<State, Double> quotas = getQuotas(stateList, divisor);
        Map<State, Double> remainders = new HashMap<>();
        for (State state : quotas.keySet()) {
            remainders.put(state,  quotas.get(state) - Math.floor(quotas.get(state)));
        }
        return remainders;
    }
}
