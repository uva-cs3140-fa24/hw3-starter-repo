package edu.virginia.sde.hw3.formats;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;

import java.util.Comparator;

/**
 * Display states sorted by population.
 */
public class PopulationFormat implements RepresentationFormat{
    private final DefaultFormatStringBuilder defaultFormatStringBuilder = new DefaultFormatStringBuilder();

    /**
     * Determines whether states are sorted by population in either {@link DisplayOrder#ASCENDING} or
     * {@link DisplayOrder#DESCENDING} order
     */
    private final DisplayOrder displayOrder;

    /**
     * Sets the display order, either {@link DisplayOrder#ASCENDING} or {@link DisplayOrder#DESCENDING}
     * @param displayOrder {@link DisplayOrder}
     */
    public PopulationFormat(DisplayOrder displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * Gets the display order
     * @return either {@link DisplayOrder#ASCENDING} or {@link DisplayOrder#DESCENDING}
     */
    public DisplayOrder getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Returns a string of states sorted by population in either descending or descending order, depending on
     * the object's configuration.
     * @param representation {@link Representation} - the results of an apportionment
     * @return a formatted {@link String}
     * @see DefaultFormatStringBuilder#getSortedFormattedString(Representation, Comparator)
     */
    @Override
    public String getFormattedString(Representation representation) {
        Comparator<State> stateComparator = Comparator.comparing(state -> state.population());
        if (displayOrder == DisplayOrder.DESCENDING) {
            stateComparator = stateComparator.reversed();
        }

        return defaultFormatStringBuilder.getSortedFormattedString(representation, stateComparator);
    }
}
