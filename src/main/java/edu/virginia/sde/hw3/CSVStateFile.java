package edu.virginia.sde.hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Represents an input CSV file for states. Specifically, this file must contain the column headers:
 * <ul>
 *     <li>"State" - the column containing the names of the states</li>
 *     <li>"Population" - the column containing the populations of each state</li>
 * </ul>
 * See sample_inputs/states.csv and sample_inputs/part2_input.csv for examples
 */
public class CSVStateFile implements StateSupplier {
    private final static String STATE_NAME_COLUMN_LABEL = "State";
    private final static String STATE_POPULATION_COLUMN_LABEL = "Population";

    /** the CSV file */
    private final File csvFile;

    /** the column index of the "State" column containing state names */
    private int stateNameColumnIndex = -1;

    /** the column index of the "Population" column containing the state populations */
    private int statePopulationColumnIndex = -1;

    /**
     * Creates a CSVStateFile object
     * @param filename the name of the csv file to open
     * @throws IllegalArgumentException if file is not a CSV file, or the file doesn't exist
     */
    public CSVStateFile(String filename) {
        if (!filename.endsWith(".csv")) {
            throw new IllegalArgumentException(
                    "edu.virginia.sde.hw3.StateCSVFile can only read CSV files. Illegal filename given: " + filename
            );
        }
        csvFile = new File(filename);
        if (!csvFile.exists()) {
            throw new IllegalArgumentException(
                    "File: " + filename + " does not exist."
            );
        }
    }

    /**
     * Returns the csv file object
     * @return {@link File}
     */
    public File getCsvFile() {
        return csvFile;
    }

    /**
     * Retrieves state data from a CSV file. See sample_inputs/states.csv for an example
     * @return a {@link List} of {@link State} objects
     */
    @Override
    public States getStates() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            String headerRow = bufferedReader.readLine();
            getTargetColumnIndices(headerRow);

            int lineNumber = 2;
            States states = new States();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                Optional<State> state = getStateFromLine(line, lineNumber);
                if (state.isEmpty()) {
                    continue;
                }
                state.ifPresent(s-> states.add(s));
                lineNumber++;
            }
            return states;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the value of {@link CSVStateFile#stateNameColumnIndex} and {@link CSVStateFile#statePopulationColumnIndex}
     * @param headerRow the first row of the CSV file
     * @throws IllegalArgumentException if file is missing either "State" or "Population" column heading
     */
    private void getTargetColumnIndices(String headerRow) {
        String[] headerRowSplit = headerRow.trim().split(",");
        for (int columnIndex = 0; columnIndex < headerRowSplit.length; columnIndex++) {
            String heading = headerRowSplit[columnIndex].trim();
            if (heading.equals(STATE_NAME_COLUMN_LABEL) && stateNameColumnIndex == -1) {
                stateNameColumnIndex = columnIndex;
            }
            if (heading.equals(STATE_POPULATION_COLUMN_LABEL) && statePopulationColumnIndex == -1) {
                statePopulationColumnIndex = columnIndex;
            }
        }
        if (stateNameColumnIndex == -1 || statePopulationColumnIndex == -1) {
            throw new IllegalArgumentException("Input file does not have correct column headers. " +
                    "Missing column labeled "+ STATE_NAME_COLUMN_LABEL +" and/or "+STATE_POPULATION_COLUMN_LABEL+".");
        }
    }

    private Optional<State> getStateFromLine(String line, int lineNumber) {
        Optional<State> state;
        try {
            state = Optional.of(getStateFromLine(line));
        } catch (ArrayIndexOutOfBoundsException e) {
            printBadLineFormatWarning(line, lineNumber);
            return Optional.empty();
        } catch (NumberFormatException e) {
            printBadPopulationWarning(line, lineNumber);
            return Optional.empty();

        }
        return state;
    }

    /**
     * Parses a line from the CSV file into a State object
     * @param line a {@link String} line from the input csv file
     * @return {@link State}
     * @throws ArrayIndexOutOfBoundsException if the line doesn't have enough columns split by comma
     * @throws NumberFormatException if the population data is not a non-negative integer
     */
    protected State getStateFromLine(String line) {
        String[] tokens = line.split(",");
        String stateName = tokens[stateNameColumnIndex].trim();
        int statePopulation = Integer.parseInt(tokens[statePopulationColumnIndex].trim());
        if (statePopulation < 0) {
            throw new NumberFormatException("State population cannot be negative.");
        }
        return new State(stateName, statePopulation);
    }

    private static void printBadLineFormatWarning(String line, int lineNumber) {
        System.out.printf("""
                Warning: Bad line format on line #%d - line = "%s"
                    Skipping line %d
                %n""", lineNumber, line, lineNumber);
    }

    private static void printBadPopulationWarning(String line, int lineNumber) {
        System.out.printf("""
                Warning: Bad population format on line #%d - line = "%s"
                    Skipping line %d
                %n""", lineNumber, line, lineNumber);
    }
}
