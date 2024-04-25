package pl.sudoku;

public class SudokuColumn extends SudokuNine {
    public SudokuColumn(SudokuField [] fields) {
        super(fields);
    }
    
    public Object clone() {
        SudokuField[] f = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            f[i] = (SudokuField) fields[i].clone();
        }
        SudokuColumn column = new SudokuColumn(f);
        return column;
    }
}
