package edu.virginia.sde.hw3;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Arguments arguments = new Arguments(args);

        Apportionment apportionment = arguments.getApportionment();
        Representation representation = apportionment.getRepresentation();

        RepresentationFormat format = arguments.getRepresentationFormat();
        System.out.println(representation.getFormattedString(format));

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
