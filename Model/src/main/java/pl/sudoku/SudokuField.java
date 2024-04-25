
package pl.sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable,  Comparable<SudokuField> {

    
    int fieldValue;
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener p) {
        changes.addPropertyChangeListener(p);
    }
    
    public int getFieldValue() {
        return fieldValue;
    }
    
    public void setFieldValue(int value) {
        int oldValue = this.fieldValue;
        if (value >= 0 && value <= 9) {
            this.fieldValue = value;
            changes.firePropertyChange("SudokuField", oldValue, value);
        }
    }
    
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuField other = (SudokuField) obj;
        return fieldValue == other.fieldValue;
    }
    
     @Override
    public int hashCode() {

        return Objects.hashCode(this.fieldValue);

    }
    
    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
            .add("value", fieldValue)
            .toString();
    }

    @Override
    public Object clone() throws CloningException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new CloningException(CloningException.MESSAGE_KEY, ex);
        }
    }
    
    @Override
    public int compareTo(SudokuField o) throws CompareToNullException {
        if (o == null) {
            throw new CompareToNullException(CompareToNullException.MESSAGE_KEY);
        }
        if (this.fieldValue > o.fieldValue) {
            return 1;
        } else if (this.fieldValue < o.fieldValue) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
