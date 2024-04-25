package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SudokuBoardTest {
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoardTest.class);
    public SudokuBoardTest() {
    }
    
    @Test
    public void testCheck() {
        
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        assertEquals(board.checkBoard(), true);
        board.set(0, 0, 1);
        board.set(0, 1, 1);
        board.set(1, 0, 1);
        assertEquals(board.checkBoard(), false);
        
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, j+1);
            }
        }
        logger.info(board.toString());
        assertEquals(board.checkBoard(), false);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, (i+j)%9+1);
            }
        }
        logger.info(board.toString());
        assertEquals(board.checkBoard(), false);
    }
    
    @Test
    public void testListening() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        board.setListening(true);
        assertTrue(board.isListening());
        int tmp = board.get(0, 0);
        board.set(0, 0, board.get(0,1));
        board.set(0, 0, tmp);
    }
    
    @Test
    public void equalsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board1 = new SudokuBoard(solver);
        assertEquals(board, board1);
        assertEquals(board, board);
        assertNotEquals(board, new SudokuField());
        assertNotEquals(board, null);
        board1.set(0,0,5);
        assertNotEquals(board, board1);
        board1.set(0,0,0);
        board1.setListening(true);
        assertNotEquals(board, board1);
    }
    
    @Test
    public void hashCodeTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board1 = new SudokuBoard(solver);
        assertEquals(board.hashCode(), board1.hashCode());
    }
    
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board1 = board.clone();
        assertNotSame(board, board1);
    }
}
