package edu.virginia.sde.hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVStateFile implements StateSupplier {
    private final static String STATE_NAME_COLUMN_LABEL = "State";
    private final static String STATE_POPULATION_COLUMN_LABEL = "Population";

    private final File csvFile;
    private int stateNameColumnIndex = -1;
    private int statePopulationColumnIndex = -1;

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

    @Override
    public List<State> getStates() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            String headerRow = bufferedReader.readLine();
            getTargetColumnIndices(headerRow);

            int lineNumber = 2;
            List<State> states = new ArrayList<State>();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                try {
                    State state = getStateFromLine(line);
                    states.add(state);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.printf("""
                            Warning: Bad line format on line #%d - line = "%s"
                                Skipping line %d
                            %n""", lineNumber, line, lineNumber);
                } catch (NumberFormatException e) {
                    System.out.printf("""
                            Warning: Bad population format on line #%d - line = "%s"
                                Skipping line %d
                            %n""", lineNumber, line, lineNumber);
                }
                lineNumber++;
            }
            return states;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    protected State getStateFromLine(String line) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] tokens = line.split(",");
        String stateName = tokens[stateNameColumnIndex].trim();
        int statePopulation = Integer.parseInt(tokens[statePopulationColumnIndex].trim());
        return new State(stateName, statePopulation);
    }
}
