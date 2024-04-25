
package pl.comp.view;

import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.Level;
import pl.sudoku.SudokuBoard;

public class Game {
    private Level level;
    private SudokuBoard sudokuBoard;
    
    public Game(final Level level) {
            this.level = level;
            this.sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
            this.sudokuBoard.solveGame();
            this.level.removeFields(sudokuBoard);
    }
    
    public SudokuBoard getSudokuBoard() {
            return this.sudokuBoard;
    }
}
