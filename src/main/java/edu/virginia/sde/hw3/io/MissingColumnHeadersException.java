package edu.virginia.sde.hw3.io;

import java.util.List;

/**
 * This exception is thrown when an input tabular file is missing required columns
 */
public class MissingColumnHeadersException extends IllegalArgumentException {
    private final List<String> missingHeaders;

    /**
     * Constructs a new MissingColumnHeadersException with a list of missing column headers.
     *
     * @param values the names of the missing columns
     */
    public MissingColumnHeadersException(String... values) {
        super(getErrorMessage(values));
        this.missingHeaders = List.of(values);
    }

    private static String getErrorMessage(String... values){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Input file is missing required column headers.\nMissing column labels:\n");
        for (String value : values) {
            stringBuilder.append(" - ")
                    .append(value)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the list of missing headers from an incorrectly formatted tabular file
     * @return a {@link List} of {@link String}s that contain the missing column labels.
     */
    public List<String> getMissingHeaders() {
        return missingHeaders;
    }
}
