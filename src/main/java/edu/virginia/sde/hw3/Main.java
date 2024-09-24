package edu.virginia.sde.hw3;

import edu.virginia.sde.hw3.algorithms.HamiltonMethod;
import edu.virginia.sde.hw3.algorithms.JeffersonMethod;
import edu.virginia.sde.hw3.formats.AlphabeticalFormat;
import edu.virginia.sde.hw3.formats.PopulationFormat;
import edu.virginia.sde.hw3.formats.RepresentationFormat;

import java.io.IOException;
import java.util.Optional;


/**
 * This program reads in a list of state names and populations and generates an apportionment, that is
 * a mapping of states to the number of representatives in the House of Representatives.<br>
 * <br>
 * This program currently supports:<br>
 * <ul>
 *     <li>Input file formats -- see sample/inputs for examples</li>
 *     <ul>
 *         <li>CSV files - see {@link CSVStateFile}</li>
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
    private static Arguments arguments;

    public static void main(String[] args) {
        arguments = new Arguments(args);
        Apportionment apportionment = arguments.getApportionment();
        Representation representation = apportionment.getRepresentation();

        generateOutput(representation);
    }

    private static void generateOutput(Representation representation) {
        RepresentationFormat format = arguments.getRepresentationFormat();
        System.out.println(representation.getFormattedString(format));

        generateFileOutput(representation);
    }

    private static void generateFileOutput(Representation representation) {
        Optional<OutputSource> outputFileOptional = arguments.getOutputFile();
        try {
            if (outputFileOptional.isPresent()) {
                OutputSource outputFile = outputFileOptional.get();
                outputFile.writeToOutput(representation);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
