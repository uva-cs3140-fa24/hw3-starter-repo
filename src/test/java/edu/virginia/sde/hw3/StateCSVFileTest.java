package edu.virginia.sde.hw3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class StateCSVFileTest {
    @Test
    public void getStates() {
        final String TEST_CSV_FILE = "csv_test_files\\states.csv";
        CSVStateFile stateCSVFile = new CSVStateFile(getResource(TEST_CSV_FILE));

        States states = stateCSVFile.getStates();
        assertIterableEquals(List.of(
                new State("Delaware", 989948),
                new State("Maryland", 6177224),
                new State("Pennsylvania", 13002700),
                new State("Virginia", 8631393),
                new State("West Virginia", 1793716)
        ), states.getStatesList());
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
