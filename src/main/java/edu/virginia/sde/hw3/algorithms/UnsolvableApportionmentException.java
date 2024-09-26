package edu.virginia.sde.hw3.algorithms;

/**
 * Thrown when a combination of state populations and target number of representatives makes it impossible
 * to use a particular Apportionment Algorithm.
 */
public class UnsolvableApportionmentException extends IllegalArgumentException {
    /**
     * Constructs a new UnsolvableApportionmentException with the specified detail message.
     *
     * @param errorMessage the detail message explaining the cause of the exception
     */
    public UnsolvableApportionmentException(String errorMessage) {
        super(errorMessage);
    }
}
