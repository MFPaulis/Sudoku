package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SudokuNineTest {
    private static final Logger logger = LoggerFactory.getLogger(SudokuNineTest.class);
    public SudokuNineTest() {
    }

    @Test
    public void testVerifyNegative() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
        }
        SudokuRow instance = new SudokuRow(fields);
        boolean expResult = false;
        boolean result = instance.verify();
        assertEquals(expResult, result);
        for (int i = 0; i < 9; i++) {
            fields[i].setFieldValue(i + 1);
        }
        expResult = true;
        result = instance.verify();
        assertEquals(expResult, result);
    }
    
    @Test
    public void equalsTest() {
        SudokuField[] fields = new SudokuField[9];
        SudokuField[] fields2 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields2[i] = new SudokuField();
        }
        SudokuRow row = new SudokuRow(fields);
        SudokuRow row2 = new SudokuRow(fields2);
        logger.info(row.toString());
        logger.info(row2.toString());
        assertEquals(row, row2);
        fields2[0].fieldValue = 1;
        assertNotEquals(row, row2);
        assertNotEquals(row, null);
        assertNotEquals(row, new SudokuField());
    }
    
    @Test
    public void hashCodeTest() {
        SudokuField[] fields = new SudokuField[9];
        SudokuField[] fields2 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields2[i] = new SudokuField();
        }
        SudokuRow row = new SudokuRow(fields);
        SudokuRow row2 = new SudokuRow(fields2);
        assertEquals(row.hashCode(), row2.hashCode());
    }
}
