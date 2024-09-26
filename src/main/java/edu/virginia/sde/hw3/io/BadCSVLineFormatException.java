package edu.virginia.sde.hw3.io;

/**
 * This exception is thrown when a line in a {@link CSVInputFile} is incorrectly formatted.
 */
public class BadCSVLineFormatException extends Exception {
    public enum BadFormatReason {
        /** This line has fewer columns than expected */
        MISSING_COLUMNS,

        /** The state name is an empty string */
        EMPTY_STATE_NAME,

        /** The population of a state is either non-integer or negative */
        BAD_POPULATION;

        /**
         * Generates an appropriate error message for a reason and the line that triggered the exception
         * @param line the CSV file line that triggered the exception
         * @return an error message explaining the source of the error.
         */
        public String getReasonMessage(String line) {
            return switch (this) {
                case MISSING_COLUMNS -> "A line doesn't have enough columns.";
                case EMPTY_STATE_NAME -> "The state name is empty.";
                case BAD_POPULATION -> "The population is not a non-negative integer.";
            } + "\"" + line + "\"";
        }
    }

    /** The reason for the exception */
    private final BadFormatReason reason;

    /** The line that triggered the exception */
    private final String line;

    /**
     * Constructs a new BadCSVLineFormatException with the specified line and reason.
     *
     * @param line the CSV file line that caused the exception
     * @param reason the {@link BadFormatReason} indicating why the line is incorrectly formatted
     */
    public BadCSVLineFormatException(String line, BadFormatReason reason) {
        super(reason.getReasonMessage(line));
        this.reason = reason;
        this.line = line;
    }

    /**
     * Retrieves the reason for the exception.
     *
     * @return the {@link BadFormatReason} indicating why the CSV line is incorrectly formatted
     */
    public BadFormatReason getReason() {
        return reason;
    }

    /**
     * Retrieves the CSV file line that caused the exception.
     *
     * @return the CSV line that triggered the exception.
     */
    public String getLine() {
        return line;
    }
}
