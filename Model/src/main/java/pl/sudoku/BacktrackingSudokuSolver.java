
package pl.sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {
    
    @Override
    public void solve(SudokuBoard board) {
        fillCell(0,0, board);
    }
    
     private boolean fillCell(int x, int y, SudokuBoard board) {
        //ArrayList<Integer> shuffled = new ArrayList<>();
        List<Integer> shuffled = Arrays.asList(new Integer[9]);
        for (int i = 1; i <= 9; i++) {
            shuffled.set(i - 1, i);
        }
        Collections.shuffle(shuffled);
        
        if (x == 9) {
            return true;
        }
        for (int i = 0; i < 9; i++) {
            boolean checkNumber = checkRow(x, y, shuffled.get(i), board)
               && checkColumn(x, y, shuffled.get(i), board)
               && checkBox(x, y, shuffled.get(i), board);
            if (checkNumber) {
                board.set(x, y, shuffled.get(i));
                int nextx = x;
                int nexty = y + 1;
                if (y == 8) {
                    nextx = x + 1;
                    nexty = 0;
                }
                if (fillCell(nextx, nexty, board)) {
                    return true;
                }
            }
        }
        board.set(x, y, 0);
        return false;
    }
    
    private boolean checkRow(int x, int y, int number, SudokuBoard board) {
        for (int i = 0; i < y; i++) {
            if (board.get(x, i) == number) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkColumn(int x, int y, int number, SudokuBoard board) {
        for (int i = 0; i < x; i++) {
            if (board.get(i, y) == number) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkBox(int x, int y, int number, SudokuBoard board) {
        x = x / 3;
        x = x * 3;
        y = y / 3;
        y = y * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board.get(i, j) == number) {
                    return false;
                }
            }
        }
        return true;
    }
}
