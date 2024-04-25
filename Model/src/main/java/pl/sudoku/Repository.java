
package pl.sudoku;

public class Repository {
    private SudokuBoard board;

    public Repository(SudokuSolver solver) {
        board = new SudokuBoard(solver);
    }

    public SudokuBoard createInstance() throws CloneNotSupportedException {
        return (SudokuBoard) board.clone();
    }
}
