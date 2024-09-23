package edu.virginia.sde.hw3;

import java.util.Comparator;

public class AlphabeticalFormat implements RepresentationFormat {
    private final DefaultFormatStringBuilder defaultFormatStringBuilder = new DefaultFormatStringBuilder();

    @Override
    public String getFormattedString(Representation representation) {
        Comparator<State> stateComparator = Comparator.comparing(state -> state.name().toUpperCase());

        return defaultFormatStringBuilder.getSortedString(representation, stateComparator);
    }

}
