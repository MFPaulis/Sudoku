
package pl.sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class SudokuNine {
    SudokuField [] fields = new SudokuField[9];
    
    public SudokuNine(SudokuField[] fields) {
        this.fields = fields;
    }
    
    public boolean verify() {
        List<Boolean> tmp = Arrays.asList(new Boolean[9]);
        Collections.fill(tmp, false);
        for (int i = 0; i < 9; i++) {
            if (fields[i].getFieldValue() == 0) {
                continue;
            }
            tmp.set(fields[i].getFieldValue() - 1, true);
        }
        return !tmp.contains(false);
    }
    
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuNine other = (SudokuNine) obj;
        return Arrays.equals(this.fields, other.fields);
    }
    
     @Override
    public int hashCode() {

        return Objects.hashCode(
            Arrays.hashCode(this.fields));

    }
    
    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
            .add("fields", fields)
            .toString();
    }
    
    
}
