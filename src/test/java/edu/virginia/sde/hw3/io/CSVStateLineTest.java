package edu.virginia.sde.hw3.io;

import edu.virginia.sde.hw3.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static edu.virginia.sde.hw3.io.BadCSVLineFormatException.BadFormatReason;

public class CSVStateLineTest {
    public static CSVInputHeadings simpleHeadings = CSVInputHeadings.getHeadings("State,Population");
    public static CSVInputHeadings extraHeadings = CSVInputHeadings.getHeadings(
            "ID, State, Capital City, Year Ratified, Population, Nickname");

    @Test
    public void getState_simpleHeadings_valid() throws BadCSVLineFormatException {
        String line = "Ohio,500";

        CSVStateLine stateLine = new CSVStateLine(line, simpleHeadings);

        State state = stateLine.getState();

        assertEquals("Ohio", state.name());
        assertEquals(500, state.population());
    }

    @Test
    public void getState_extraHeadings_spaces_valid() throws BadCSVLineFormatException {
        String line = "1, Ohio ,Columbus,1803, 500 ,Buckeye State";

        CSVStateLine stateLine = new CSVStateLine(line, extraHeadings);

        State state = stateLine.getState();

        assertEquals("Ohio", state.name());
        assertEquals(500, state.population());
    }

    /**
     * Even though the relevant state data is there, for defensive programming reasons, we throw exception on
     * every line with *any* missing columns
     */
    @Test
    public void getState_BadCSVLineFormatException_missingColumns()  {
        String line = "1,Ohio,Columbus,1803,500";

        BadCSVLineFormatException exception = assertThrows(BadCSVLineFormatException.class, () -> {
            CSVStateLine csvStateLine = new CSVStateLine(line, extraHeadings);
            csvStateLine.getState();
        });

        assertEquals(BadFormatReason.MISSING_COLUMNS, exception.getReason());
        assertEquals(line, exception.getLine());
    }

    @Test
    public void getState_BadCSVLineFormatException_emptyStateName()  {
        String line = "1,,Columbus,1803,500,Buckeye State";

        BadCSVLineFormatException exception = assertThrows(BadCSVLineFormatException.class, () -> {
            CSVStateLine csvStateLine = new CSVStateLine(line, extraHeadings);
            csvStateLine.getState();
        });

        assertEquals(BadFormatReason.EMPTY_STATE_NAME, exception.getReason());
        assertEquals(line, exception.getLine());
    }

    @Test
    public void getState_BadCSVLineFormatException_populationNonNumber()  {
        String line = "1,Ohio,Columbus,1803,Five Hundred,Buckeye State";

        BadCSVLineFormatException exception = assertThrows(BadCSVLineFormatException.class, () -> {
            CSVStateLine csvStateLine = new CSVStateLine(line, extraHeadings);
            csvStateLine.getState();
        });

        assertEquals(BadFormatReason.BAD_POPULATION, exception.getReason());
        assertEquals(line, exception.getLine());
    }

    @Test
    public void getState_BadCSVLineFormatException_populationNegative()  {
        String line = "1,Ohio,Columbus,1803,-500,Buckeye State";

        BadCSVLineFormatException exception = assertThrows(BadCSVLineFormatException.class, () -> {
            CSVStateLine csvStateLine = new CSVStateLine(line, extraHeadings);
            csvStateLine.getState();
        });

        assertEquals(BadFormatReason.BAD_POPULATION, exception.getReason());
        assertEquals(line, exception.getLine());
    }
}
