package edu.virginia.sde.hw3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * This class handles parsing the program's command line arguments to configure the apportionment, including
 * identifying the input source, selecting the algorithm, number of representatives, and determining how output
 * is generated.
 */
public class Arguments {
    /** the default number of seats in the US House of representatives */
    public static final int DEFAULT_REPRESENTATIVES = 435;

    /** the command line arguments passed to the program */
    private final List<String> args;

    /**
     * @param args the command line arguments to the program.
     * @throws IllegalArgumentException if arguments are empty
     */
    public Arguments(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing command line arguments! You must include at least one argument for the filename");
        }
        this.args = Arrays.asList(args);
    }

    /**
     * Returns the StateSupplier with access to the data source that the state population data is pulled from.
     * @return {@link StateSupplier}
     * @throws IllegalArgumentException if unsupported file format used
     */
    public StateSupplier getStateSupplier() {
        String filename = args.getFirst();
        if (filename.endsWith(".csv")) {
            return new CSVStateFile(filename);
        }
        throw new IllegalArgumentException("Unsupported input file type: " + filename + "\n" +
                "\tThis program currently only supports CSV files");
    }

    /**
     * Returns the number of representatives to apportion for the House of Representatives. By default, the House
     * of Representatives has 435 representatives.
     * @return number of representatives to allocate
     * @throws IllegalArgumentException if non-positive number of representatives passed in.
     */
    public int getTargetRepresentatives() {
        if (args.size() < 2) {
            return DEFAULT_REPRESENTATIVES;
        }

        try {
            int targetRepresentatives = Integer.parseInt(args.get(1));
            if (targetRepresentatives <= 0) {
                throw new IllegalArgumentException("Target representatives must be a positive integer");
            }
            return targetRepresentatives;
        } catch (NumberFormatException e) {
            return DEFAULT_REPRESENTATIVES;
        }
    }

    /**
     * Gets the apportionment algorithm to use. By default, we use {@link JeffersonMethod the Jefferson Method}, but
     * the {@link HamiltonMethod can be selected with the --hamilton flag}
     * @return {@link ApportionmentMethod}
     */
    public ApportionmentMethod getApportionmentMethod() {
        if (args.contains("--hamilton")) {
            return new HamiltonMethod();
        }
        return new JeffersonMethod();
    }

    /**
     * Returns how to display the apportionment to the console.
     * @returns {@link RepresentationFormat}
     */
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

    /**
     * Returns an assembled Apportionment object using {@link StateSupplier}, {@link ApportionmentMethod}, and
     * the target number of representatives
     * @return {@link Apportionment}
     */
    public Apportionment getApportionment() {
        StateSupplier stateSupplier = getStateSupplier();
        ApportionmentMethod apportionmentMethod = getApportionmentMethod();
        int targetRepresentatives = getTargetRepresentatives();
        return new Apportionment(stateSupplier, apportionmentMethod, targetRepresentatives);
    }

    /**
     * Gets the OutputSource if specified by the user for outputting to a file or other resource.
     * @return an {@link Optional} of {@link OutputSource}
     */
    public Optional<OutputSource> getOutputFile() {
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
