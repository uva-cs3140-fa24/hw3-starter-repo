package edu.virginia.sde.hw3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Arguments {
    private final List<String> args;

    public Arguments(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing command line arguments! You must include at least one argument for the filename");
        }
        this.args = Arrays.asList(args);
    }

    public StateSupplier getStateSupplier() {
        String filename = args.getFirst();
        if (filename.endsWith(".csv")) {
            return new CSVStateFile(filename);
        }
        throw new IllegalArgumentException("Unsupported input file type: " + filename + "\n" +
                "\tThis program currently only supports CSV files");
    }

    public int getTargetRepresentatives() {
        if (args.size() < 2) {
            return 435;
        }

        try {
            int targetRepresentatives = Integer.parseInt(args.get(1));
            if (targetRepresentatives <= 0) {
                throw new IllegalArgumentException("Target representatives must be a positive integer");
            }
            return targetRepresentatives;
        } catch (NumberFormatException e) {
            return 435;
        }
    }

    public ApportionmentMethod getApportionmentMethod() {
        if (args.contains("--hamilton")) {
            return new HamiltonMethod();
        }
        return new JeffersonMethod();
    }

    public RepresentationFormat getRepresentationFormat() {
        if (args.contains("--population")) {
            if (args.contains("--ascending")) {
                return new PopulationFormat(DisplayOrder.ASCENDING);
            } else {
                return new PopulationFormat(DisplayOrder.DESCENDING);
            }
        }
        return new AlphabeticalFormat();
    }

    public Apportionment getApportionment() {
        StateSupplier stateSupplier = getStateSupplier();
        ApportionmentMethod apportionmentMethod = getApportionmentMethod();
        int targetRepresentatives = getTargetRepresentatives();
        return new Apportionment(stateSupplier, apportionmentMethod, targetRepresentatives);
    }

    public Optional<OutputFile> getOutputFile() {
        int index = args.indexOf("--out");
        if (index == -1) {
            return Optional.empty();
        }
        if (args.size() < index + 1) {
            throw new IllegalArgumentException("No file specified after out");
        }

        String outputFilename = args.get(index + 1);
        if (!outputFilename.endsWith(".csv")) {
            throw new IllegalArgumentException("Unsupported Output file format. Currently only .csv supported");
        }
        return Optional.of(new CSVOutputFile(outputFilename));
    }
}
