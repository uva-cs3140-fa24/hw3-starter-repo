package edu.virginia.sde.hw3;

import edu.virginia.sde.hw3.algorithms.HamiltonMethod;
import edu.virginia.sde.hw3.algorithms.JeffersonMethod;
import edu.virginia.sde.hw3.formats.AlphabeticalFormat;
import edu.virginia.sde.hw3.formats.PopulationFormat;
import edu.virginia.sde.hw3.formats.RepresentationFormat;
import edu.virginia.sde.hw3.io.CSVOutputFile;
import edu.virginia.sde.hw3.io.CSVInputFile;
import edu.virginia.sde.hw3.io.OutputSource;

import java.io.IOException;


/**
 * This program reads in a list of state names and populations and generates an apportionment, that is
 * a mapping of states to the number of representatives in the House of Representatives.<br>
 * <br>
 * This program currently supports:<br>
 * <ul>
 *     <li>Input file formats -- see sample_inputs folder for examples</li>
 *     <ul>
 *         <li>CSV files - see {@link CSVInputFile}</li>
 *     </ul>
 *     <li>Apportionment Methods</li>
 *     <ul>
 *         Hamilton Method - see {@link HamiltonMethod}
 *         Jefferson Method - see {@link JeffersonMethod}
 *     </ul>
 *     <li>Output Formats:</li>
 *     <ul>
 *         Sorted alphabetically - see {@link AlphabeticalFormat}
 *         Sorted by population - see {@link PopulationFormat}
 *     </ul>
 *     <li>File output format</li>
 *     <ul>
 *         .csv file - see {@link CSVOutputFile}
 *     </ul>
 * </ul>
 */
public class Main {
    /**
     * Main method for running from command line arguments
     * @param args the command line arguments from terminal
     */
    public static void main(String[] args) {
        Arguments arguments = new Arguments(args);

        Apportionment apportionment = arguments.getApportionment();

        Representation representation = apportionment.getRepresentation();

        RepresentationFormat format = arguments.getRepresentationFormat();
        System.out.println(representation.getFormattedString(format));

        arguments.getOutputFile().ifPresent(
                outputSource -> outputToSource(representation, outputSource)
        );
    }

    private static void outputToSource(Representation representation, OutputSource outputSource) {
        try {
            outputSource.writeToOutput(representation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
