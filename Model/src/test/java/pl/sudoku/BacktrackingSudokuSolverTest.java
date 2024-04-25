package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BacktrackingSudokuSolverTest {
    private static final Logger logger = LoggerFactory.getLogger(BacktrackingSudokuSolver.class);
    public BacktrackingSudokuSolverTest() {
    }

    @Test
    public void testSolve() {
        logger.info("solve");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        logger.info(board.toString());
        checkRows(board);
        checkColumns(board);
        checkBoxes(board);
    }
    
    private void checkRows(SudokuBoard board)
    {
        boolean[] numbers = new boolean[9];
        /*for (int i = 0; i < 9; i++) {
            numbers[i] = false;
            System.out.print(numbers[i]);
        }*/
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numbers[board.get(i, j) - 1] = true;
            }
            for (int j = 0; j < 9; j++) {
                assertEquals(numbers[j], true);
                numbers[j] = false;
            }
        }
    }
    
    private void checkColumns(SudokuBoard board)
    {
        boolean[] numbers = new boolean[9];
         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numbers[board.get(j, i) - 1] = true;
            }
            for (int j = 0; j < 9; j++) {
                assertEquals(numbers[j], true);
                numbers[j] = false;
            }
        }
    }
    
    private void checkBoxes(SudokuBoard board)
    {
        boolean[] numbers = new boolean[9];
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for(int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        numbers[board.get(k, l) - 1] = true;
                    }
                }
                for (int k = 0; k < 9; k++) {
                assertEquals(numbers[k], true);
                numbers[k] = false;
                }
            }
        }
    }
     
    @Test
    public void testFillBoardDifferently() {
        logger.info("fillBoardDifferently");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);
        board.solveGame();
        board2.solveGame();
        boolean different = false;
        for (int i = 0; i < 9;  i++) {
            for (int j = 0; j < 9; j++) {
                if(board.get(i,j) != board2.get(i,j)) {
                    different = true;
                    break;
                }
            }
            if (different) {
                break;
            }
        }
        assertEquals(different, true);
    }
}
