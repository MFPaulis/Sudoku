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
public class SudokuColumnTest {
    
    public SudokuColumnTest() {
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
        }
        SudokuColumn sudokuColumn = new SudokuColumn(fields);
        SudokuColumn sudokuColumn1 = (SudokuColumn) sudokuColumn.clone();
        assertEquals(sudokuColumn, sudokuColumn1);
        sudokuColumn.fields[0].setFieldValue(1);
        assertNotEquals(sudokuColumn, sudokuColumn1);
    }
    
}
