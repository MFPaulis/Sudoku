
package pl.sudoku;

public class SudokuBox extends SudokuNine implements Cloneable {
    public SudokuBox(SudokuField[] fields) {
        super(fields);
    }
    
    public Object clone() {
        SudokuField[] f = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            f[i] = (SudokuField) fields[i].clone();
        }
        SudokuBox box = new SudokuBox(f);
        return box;
    }
}
