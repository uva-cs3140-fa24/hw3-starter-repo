package edu.virginia.sde.hw3;

import java.util.Comparator;

public class PopulationFormat implements RepresentationFormat{
    private final DefaultFormatStringBuilder defaultFormatStringBuilder = new DefaultFormatStringBuilder();
    private final DisplayOrder displayOrder;


    public PopulationFormat(DisplayOrder displayOrder) {
        this.displayOrder = displayOrder;
    }

    public DisplayOrder getDisplayOrder() {
        return displayOrder;
    }

    @Override
    public String getFormattedString(Representation representation) {
        Comparator<State> stateComparator = Comparator.comparing(state -> state.population());
        if (displayOrder == DisplayOrder.DESCENDING) {
            stateComparator = stateComparator.reversed();
        }

        return defaultFormatStringBuilder.getSortedString(representation, stateComparator);
    }
}
