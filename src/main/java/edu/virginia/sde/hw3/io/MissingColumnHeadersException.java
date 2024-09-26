package edu.virginia.sde.hw3.io;

import java.util.List;

public class MissingColumnHeadersException extends IllegalArgumentException {
    private final List<String> missingHeaders;

    public MissingColumnHeadersException(String... values) {
        super(getErrorMessage(values));
        this.missingHeaders = List.of(values);
    }

    public static String getErrorMessage(String... values){
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
