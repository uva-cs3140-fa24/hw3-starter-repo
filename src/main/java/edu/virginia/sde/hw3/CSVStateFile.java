package edu.virginia.sde.hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents an input CSV file for states. Specifically, this file must contain the column headers:
 * <ul>
 *     <li>"State" - the column containing the names of the states</li>
 *     <li>"Population" - the column containing the populations of each state</li>
 * </ul>
 * See sample_inputs/part1_input.csv and sample_inputs/part2_input.csv for examples
 */
public class CSVStateFile implements StateSupplier {
    /** Required Column Header for State Name */
    private final static String STATE_NAME_COLUMN_LABEL = "State";

    /** Required Column Header for State Population */
    private final static String STATE_POPULATION_COLUMN_LABEL = "Population";

    /** the CSV file */
    private final File csvFile;

    private int numberOfColumns;

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
            getHeaderInformation(bufferedReader);
            return getStatesFromContents(bufferedReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getHeaderInformation(BufferedReader bufferedReader) throws IOException {
        String headerRow = bufferedReader.readLine();
        numberOfColumns = headerRow.split(",").length;
        getTargetColumnIndices(headerRow);
    }

    private void getTargetColumnIndices(String headerRow) throws MissingColumnHeadersException {
        String[] headerRowSplit = headerRow.trim().split(",");
        for (int columnIndex = 0; columnIndex < headerRowSplit.length; columnIndex++) {
            String heading = headerRowSplit[columnIndex].trim();
            if (isStateNameHeader(heading)) {
                stateNameColumnIndex = columnIndex;
            }
            if (isStatePopulationHeader(heading)) {
                statePopulationColumnIndex = columnIndex;
            }
        }

        List<String> missingLabels = new ArrayList<>();
        if (stateNameColumnIndex == -1) {
            missingLabels.add(STATE_NAME_COLUMN_LABEL);
        }

        if (statePopulationColumnIndex == -1) {
            missingLabels.add(STATE_POPULATION_COLUMN_LABEL);
        }

        if (!missingLabels.isEmpty()) {
            throw new MissingColumnHeadersException(missingLabels.toArray(new String[0]));
        }
    }

    private boolean isStateNameHeader(String heading) {
        return heading.equals(STATE_NAME_COLUMN_LABEL) && stateNameColumnIndex == -1;
    }

    private boolean isStatePopulationHeader(String heading) {
        return heading.equals(STATE_POPULATION_COLUMN_LABEL) && statePopulationColumnIndex == -1;
    }

    private States getStatesFromContents(BufferedReader bufferedReader) throws IOException {
        int lineNumber = 2; // starting on the line *after* the header row
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
    }

    private Optional<State> getStateFromLine(String line, int lineNumber) {
        if (line.split(",").length != numberOfColumns) {
            printBadLineFormatWarning(line, lineNumber);
            return Optional.empty();
        }

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

    private static void printBadLineFormatWarning(String line, int lineNumber) {
        System.out.printf("""
                Warning: Bad line format on line #%d - line = "%s"
                    Skipping line %d
                %n""", lineNumber, line, lineNumber);
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

    private static void printBadPopulationWarning(String line, int lineNumber) {
        System.out.printf("""
                Warning: Bad population format on line #%d - line = "%s"
                    Skipping line %d
                %n""", lineNumber, line, lineNumber);
    }
}
