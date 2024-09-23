package edu.virginia.sde.hw3;

import java.io.IOException;

public interface OutputSource {
    void writeToOutput(Representation representation) throws IOException;
}
