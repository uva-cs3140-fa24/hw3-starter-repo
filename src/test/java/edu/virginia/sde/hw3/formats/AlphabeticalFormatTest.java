package edu.virginia.sde.hw3.formats;

import edu.virginia.sde.hw3.Representation;
import edu.virginia.sde.hw3.State;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AlphabeticalFormatTest {
    static final State OH = new State("Ohio", 300);
    static final State VA = new State("Virginia", 150);
    static final State DE = new State("Delaware", 50);

    @Test
    void getFormattedString() {
        Representation representation = new Representation(Map.of(
                OH, 6,
                VA, 3,
                DE, 1
        ));

        RepresentationFormat representationFormat = new AlphabeticalFormat();
        String formattedString = representationFormat.getFormattedString(representation);

        String[] lines = formattedString.split("\n");
        assertTrue(lines[0].startsWith("State"), "First line of formatted String isn't header row");
        assertTrue(lines[1].startsWith(DE.name()), "First line of data isn't for" + DE.name());
        assertTrue(lines[2].startsWith(OH.name()), "Second line of data isn't for" + DE.name());
        assertTrue(lines[3].startsWith(VA.name()), "Third line of data isn't for" + DE.name());
    }
}