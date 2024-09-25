package edu.virginia.sde.hw3;

public class MissingColumnHeadersException extends IllegalArgumentException {
    public MissingColumnHeadersException(String... values) {
        super(getErrorMessage(values));
    }

    public static String getErrorMessage(String... values){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Input file does not have correct column headers.\nMissing column labels:\n");
        for (String value : values) {
            stringBuilder.append(" - " + value + "\n");
        }
        return stringBuilder.toString();
    }
}
