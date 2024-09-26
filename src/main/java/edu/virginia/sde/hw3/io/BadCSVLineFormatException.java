package edu.virginia.sde.hw3.io;

public class BadCSVLineFormatException extends Exception {
    public enum BadFormatReason {
        MISSING_COLUMNS,
        EMPTY_STATE_NAME,
        BAD_POPULATION;

        public String getReasonMessage(String line) {
            return switch (this) {
                case MISSING_COLUMNS -> "A line doesn't have enough columns.";
                case EMPTY_STATE_NAME -> "The state name is empty.";
                case BAD_POPULATION -> "The population is not a non-negative integer.";
            } + "\"" + line + "\"";
        }
    }

    private final BadFormatReason reason;
    private final String line;

    public BadCSVLineFormatException(String line, BadFormatReason reason) {
        super(reason.getReasonMessage(line));
        this.reason = reason;
        this.line = line;
    }

    public BadFormatReason getReason() {
        return reason;
    }

    public String getLine() {
        return line;
    }
}
