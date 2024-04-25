package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static pl.sudoku.Level.EASY;
import static pl.sudoku.Level.HARD;
import static pl.sudoku.Level.MEDIUM;

public class LevelTest {
    
    public LevelTest() {
    }

    @Test
    public void testEasyRemoveFields() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        EASY.removeFields(sudokuBoard);
        int emptyFields = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.get(i,j) == 0) {
                    emptyFields++;
                }
            }
        }
        assertEquals(emptyFields, 20);
    }
    
    @Test
    public void testMediumRemoveFields() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        MEDIUM.removeFields(sudokuBoard);
        int emptyFields = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.get(i,j) == 0) {
                    emptyFields++;
                }
            }
        }
        assertEquals(emptyFields, 30);
    }
    
    @Test
    public void testHardRemoveFields() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        HARD.removeFields(sudokuBoard);
        int emptyFields = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.get(i,j) == 0) {
                    emptyFields++;
                }
            }
        }
        assertEquals(emptyFields, 50);
    }
}
