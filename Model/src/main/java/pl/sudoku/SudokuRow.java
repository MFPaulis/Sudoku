
package pl.sudoku;


public class SudokuRow extends SudokuNine {
    public SudokuRow(SudokuField [] fields) {
        super(fields);
    }
    
    public Object clone() {
        SudokuField[] f = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            f[i] = (SudokuField) fields[i].clone();
        }
        SudokuRow row = new SudokuRow(f);
        return row;
    }
}
