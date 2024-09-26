package edu.virginia.sde.hw3.io;

import edu.virginia.sde.hw3.State;
import edu.virginia.sde.hw3.States;

import java.io.*;
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
public class CSVInputFile implements StateSource {
    /** CSV file name */
    private final String filename;

    /** The CSV file */
    private final File csvFile;

    /** The CSV headers */
    private CSVInputHeadings headings;

    /**
     * Creates a CSVStateFile object
     * @param filename the name of the csv file to open
     * @throws IllegalArgumentException if file is not a CSV file, or the file doesn't exist
     */
    public CSVInputFile(String filename) {
        if (!filename.endsWith(".csv")) {
            throw new IllegalArgumentException(
                    "CSVInputFile can only read CSV files. Illegal filename given: " + filename
            );
        }
        this.filename = filename;
        csvFile = new File(filename);
        if (!csvFile.exists()) {
            throw new IllegalArgumentException(String.format("File not found: %s does not exist.", filename));
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
            headings = CSVInputHeadings.getHeadings(headerRow);

            int lineNumber = 2; // starting on the line *after* the header row
            States states = new States();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                Optional<State> state = getStateFromLine(line, lineNumber);
                state.ifPresent(s-> states.add(s));
                lineNumber++;
            }
            return states;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<State> getStateFromLine(String line, int lineNumber) {
        try {
            CSVStateLine stateLine = new CSVStateLine(line, headings);
            return Optional.of(stateLine.getState());
        } catch (BadCSVLineFormatException e) {
            printBadLineFormatWarning(e, lineNumber);
            return Optional.empty();
        }
    }

    private void printBadLineFormatWarning(BadCSVLineFormatException exception, int lineNumber) {
        System.out.printf("""
                Warning: %s line %d - %s
                """, filename, lineNumber, exception.getMessage());
    }
}
