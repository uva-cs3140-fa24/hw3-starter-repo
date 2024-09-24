package edu.virginia.sde.hw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CSVOutputFile implements OutputSource {
    private final String outputFilename;

    public CSVOutputFile(String outputFilename) {
        if (outputFilename == null) {
            throw new IllegalArgumentException("CSVOutputFile filename cannot be null");
        }
        this.outputFilename = outputFilename;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    @Override
    public void writeToOutput(Representation representation) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            writer.write("State,Representatives\n");

            List<State> sortedStates = new ArrayList<>(representation.getStates());
            sortedStates.sort(Comparator.comparing(state -> state.name()));

            List<String> lines = new ArrayList<>();
            for (State state: sortedStates) {
                lines.add(String.format("%s,%d\n", state.name(), representation.getSeats(state)));
            }

            for (String line : lines) {
                writer.write(line);
            }
        }
    }
}
