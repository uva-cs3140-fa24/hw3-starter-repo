package edu.virginia.sde.hw3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Representation implements Iterable<State>{
    Map<State, Integer> representation;

    public Representation(Map<State, Integer> representation) {
        this.representation = representation;
    }

    public Representation() {
        this(new HashMap<>());
    }

    public int size() {
        return representation.size();
    }

    public void putRepresentatives(State state, int numRepresentatives) {
        representation.put(state, numRepresentatives);
    }

    public void addRepresentatives(State state, int numRepresentatives) {
        int totalRepresentatives = numRepresentatives + representation.getOrDefault(state, 0);
        representation.put(state, totalRepresentatives);
    }

    public int getRepresentatives(State state) {
        return representation.getOrDefault(state, 0);
    }

    public int getTotalRepresentatives() {
        return representation.values()
                .stream()
                .reduce(0, Integer::sum);
    }

    public Set<State> getStates() {
        return representation.keySet();
    }

    public String toString() {
        return representation.toString();
    }

    public String getFormattedString(RepresentationFormat format) {
        return format.getFormattedString(this);
    }

    @Override
    public Iterator<State> iterator() {
        return representation.keySet().iterator();
    }
}
