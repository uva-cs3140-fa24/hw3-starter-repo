package edu.virginia.sde.hw3;

import edu.virginia.sde.hw3.formats.CSVFormat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an output in CSV format. For example: <br>
 * <i>
 *     State,Representatives<br>
 *     Alabama,7<br>
 *     Arizona,10<br>
 *     Alaska,1<br>
 *     etc.
 * </i>
 */
public class CSVOutputFile implements OutputSource {
    /** the location of the file to write the output to */
    private final String outputFilename;

    /**
     * Creates a new CSVOutputFile object
     * @param outputFilename the filename of the file to be written to
     */
    public CSVOutputFile(String outputFilename) {
        if (outputFilename == null) {
            throw new IllegalArgumentException("CSVOutputFile filename cannot be null");
        }
        if (!outputFilename.endsWith(".csv")) {
            throw new IllegalArgumentException("Cannot write to a non-csv file: " + outputFilename);
        }
        this.outputFilename = outputFilename;
    }

    /**
     * Gets the filename of the output file
     * @return filename
     */
    public String getOutputFilename() {
        return outputFilename;
    }

    /**
     * Writes the apportionment results to the output csv file showing the state name and the allocated representatives
     * @param representation {@link Representation} the results of an apportionment algorithm
     * @throws IOException if there are any failures when writing to the file
     */
    @Override
    public void writeToOutput(Representation representation) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            String fileContents = representation.getFormattedString(new CSVFormat());
            writer.write(fileContents);
        }
    }
}


