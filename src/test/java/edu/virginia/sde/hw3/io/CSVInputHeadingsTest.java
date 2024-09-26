package edu.virginia.sde.hw3.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.virginia.sde.hw3.io.CSVInputHeadings.STATE_NAME_COLUMN_LABEL;
import static edu.virginia.sde.hw3.io.CSVInputHeadings.STATE_POPULATION_COLUMN_LABEL;
import static org.junit.jupiter.api.Assertions.*;

public class CSVInputHeadingsTest {
    @Test
    @DisplayName("getHeadings(\"State,Population\")")
    public void getHeadings_twoColumns_correctLabels() {
        String headerRow = "State,Population";

        CSVInputHeadings headings = CSVInputHeadings.getHeadings(headerRow);

        assertEquals(2, headings.getNumberOfColumns());
        assertEquals(0, headings.getStateNameColumnIndex());
        assertEquals(1, headings.getStatePopulationColumnIndex());
    }

    @Test
    @DisplayName("getHeadings(\"State,Population\")")
    public void getHeadings_extraColumns_correctLabels_leadingSpaces() {
        String headerRow = "ID, State, Capital City, Year Ratified, Population, Nickname";

        CSVInputHeadings headings = CSVInputHeadings.getHeadings(headerRow);

        assertEquals(6, headings.getNumberOfColumns());
        assertEquals(1, headings.getStateNameColumnIndex());
        assertEquals(4, headings.getStatePopulationColumnIndex());
    }

    @Test
    @DisplayName("getHeadings(\"State,Population\")")
    public void getHeadings_missingPopulation() {
        String headerRow = "ID, State, Capital City, Year Ratified, Nickname";

        MissingColumnHeadersException e = assertThrows(MissingColumnHeadersException.class, () ->
                CSVInputHeadings.getHeadings(headerRow));

        List<String> missingHeaders = e.getMissingHeaders();
        assertTrue(missingHeaders.contains(STATE_POPULATION_COLUMN_LABEL),
                "Missing headers incorrectly does not contain " + STATE_POPULATION_COLUMN_LABEL);
        assertFalse(missingHeaders.contains(STATE_NAME_COLUMN_LABEL),
                "Missing headers incorrectly contains " + STATE_NAME_COLUMN_LABEL);
    }


}
