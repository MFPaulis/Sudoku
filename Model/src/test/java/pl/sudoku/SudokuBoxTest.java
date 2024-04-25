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
public class SudokuBoxTest {
    
    public SudokuBoxTest() {
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
        }
        SudokuBox sudokuBox = new SudokuBox(fields);
        SudokuBox sudokuBox1 = (SudokuBox) sudokuBox.clone();
        assertEquals(sudokuBox, sudokuBox1);
        sudokuBox.fields[0].setFieldValue(1);
        assertNotEquals(sudokuBox, sudokuBox1);
    }
}
