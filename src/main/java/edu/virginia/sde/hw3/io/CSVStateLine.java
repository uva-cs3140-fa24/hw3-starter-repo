package edu.virginia.sde.hw3.io;

import edu.virginia.sde.hw3.State;

import static edu.virginia.sde.hw3.io.BadCSVLineFormatException.BadFormatReason;

public class CSVStateLine {
    private CSVInputHeadings headings;
    private String line;
    private String[] lineData;

    public CSVStateLine(String line, CSVInputHeadings headings) throws BadCSVLineFormatException {
        this.headings = headings;
        this.line = line;
        this.lineData = line.split(",");
        if (lineData.length != headings.getNumberOfColumns()) {
            throw new BadCSVLineFormatException(line, BadFormatReason.MISSING_COLUMNS);
        }
    }

    public State getState() throws BadCSVLineFormatException {
        String name = lineData[headings.getStateNameColumnIndex()].strip();
        if (name.isEmpty()) {
            throw new BadCSVLineFormatException(line, BadFormatReason.EMPTY_STATE_NAME);
        }

        int population;
        try {
            population = Integer.parseInt(lineData[headings.getStatePopulationColumnIndex()].strip());
        } catch (NumberFormatException e) {
            throw new BadCSVLineFormatException(line, BadFormatReason.BAD_POPULATION);
        }
        if (population < 0) {
            throw new BadCSVLineFormatException(line, BadFormatReason.BAD_POPULATION);
        }

        return new State(name, population);
    }
}
