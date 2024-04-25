/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paulina
 */
public class SudokuRowTest {
    
    public SudokuRowTest() {
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
        }
        SudokuRow sudokuRow = new SudokuRow(fields);
        SudokuRow sudokuRow1 = (SudokuRow) sudokuRow.clone();
        assertEquals(sudokuRow, sudokuRow1);
        sudokuRow.fields[0].setFieldValue(1);
        assertNotEquals(sudokuRow, sudokuRow1);
    }
    
}
