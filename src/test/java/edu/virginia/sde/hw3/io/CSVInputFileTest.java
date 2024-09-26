package edu.virginia.sde.hw3.io;

import edu.virginia.sde.hw3.State;
import edu.virginia.sde.hw3.States;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class CSVInputFileTest {
    @Test
    public void getStates() {
        final String TEST_CSV_FILE = "csv_test_files\\states.csv";
        CSVInputFile stateCSVFile = new CSVInputFile(getResource(TEST_CSV_FILE));

        States states = stateCSVFile.getStates();
        assertIterableEquals(new HashSet(Set.of(
                new State("Delaware", 989948),
                new State("Maryland", 6177224),
                new State("Pennsylvania", 13002700),
                new State("Virginia", 8631393),
                new State("West Virginia", 1793716)
        )), states.getStates());
    }

    @SuppressWarnings("SameParameterValue")
    private String getResource(String resourceName) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            return Objects.requireNonNull(classLoader.getResource(resourceName)).toURI().getPath();
        } catch (Exception e) {
            throw new RuntimeException("Error! The resource was unable to be loaded.");
        }
    }
}
