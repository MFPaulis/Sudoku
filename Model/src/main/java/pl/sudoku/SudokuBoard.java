package pl.sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuBoard implements java.beans.PropertyChangeListener, Serializable, Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private boolean listening;
    private SudokuField[][] board  = new SudokuField [9][9];
    
    private SudokuSolver sudokuSolver;
    
    public SudokuBoard(SudokuSolver sudokuSolver) {
        listening = false;
        this.sudokuSolver = sudokuSolver;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField(); 
                board[i][j].addPropertyChangeListener(this);
            }
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (listening && !checkBoard()) {
            logger.info("Niepoprawna plansza!");
        }
    }
    
    
    public boolean isListening() {
        return listening;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
    }
    
    public int get(int x, int y) {
        
        return board[x][y].getFieldValue();
    }
    
    public SudokuField getField(int x, int y) {
        return board[x][y];
    }
    
    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }
    
    public void solveGame() {
        sudokuSolver.solve(this);
    }
    
    public SudokuRow getRow(int x) {
        SudokuField[] tmp = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            tmp[i] = board[x][i];
        }
        SudokuRow row = new SudokuRow(tmp);
        return row;
    }
    
    public SudokuColumn getColumn(int y) {
        SudokuField[] tmp = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            tmp[i] = board[i][y];
        }
        SudokuColumn column = new SudokuColumn(tmp);
        return column;
    }
    
    public SudokuBox getBox(int x, int y) {
        SudokuField[] tmp = new SudokuField[9];
        x = x * 3;
        y = y * 3;
        
        int k = 0;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                tmp[k] = board[i][j];
                k++;
            }
        }
        SudokuBox box = new SudokuBox(tmp);
        return box;
    }
    
    public boolean checkBoard() {
        if (checkRows() && checkColumns() && checkBoxes()) {
            return true;
        }
        return false;
    }
    
    public boolean checkRows() {
        for (int i = 0; i < 9; i++) {
           SudokuRow row = getRow(i);
           if (!row.verify()) {
               return false;
           }
        }
        return true;
          
    }
    
    public boolean checkColumns() {
        for (int i = 0; i < 9; i++) {
           SudokuColumn column = getColumn(i);
           if (!column.verify()) {
               return false;
           }
        }
        return true;
    }
    
    public boolean checkBoxes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SudokuBox box = getBox(i, j);
                if (!box.verify()) {
                    return false;
                }   
            }
        }
        return true;
    }
    /*
    @Override
    public String toString() {
        String string = new String();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                string += board[i][j].getFieldValue();
            }
            string += "\n";
        }
        return string;
    }*/
    
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuBoard other = (SudokuBoard) obj;
        return listening == other.listening
                && Arrays.deepEquals(this.board, other.board);
    }
    
     @Override
    public int hashCode() {

        return Objects.hashCode(
            this.listening, Arrays.deepHashCode(this.board));

    }
    
    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
            .add("listening", listening)
            .add("board", board)
            .toString();
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard sudokuBoard = new SudokuBoard(this.sudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, this.get(i, j));
            }
        }
        sudokuBoard.listening = this.listening;
        return sudokuBoard;
    }
}
