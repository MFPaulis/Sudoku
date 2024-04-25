package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SudokuFieldTest {
    
    public SudokuFieldTest() {
    }

    @Test
    public void testSetWrongFieldValue() {
        SudokuField instance = new SudokuField();
        instance.setFieldValue(1);
        instance.setFieldValue(10);
        assertEquals(instance.getFieldValue(), 1);
        instance.setFieldValue(-1);
        assertEquals(instance.getFieldValue(), 1);
    }
    
    @Test
    public void equalsTest() {
        SudokuField field = new SudokuField();
        field.setFieldValue(1);
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(1);
        SudokuField field2 = new SudokuField();
        field2.setFieldValue(2);
        assertEquals(field, field1);
        assertNotEquals(field, field2);
        assertNotEquals(field, null);
        assertNotEquals(field, new SudokuRow(new SudokuField[9]));
    }
    
    @Test
    public void hashCodeTest() {
        SudokuField field = new SudokuField();
        field.setFieldValue(1);
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(1);
        assertEquals(field.hashCode(), field1.hashCode());
    }
    
    @Test
    public void copyTest() throws CloneNotSupportedException {

        SudokuField field = new SudokuField();
        field.setFieldValue(3);
        SudokuField field2 = (SudokuField) field.clone();
        assertEquals(field, field2);
        field.setFieldValue(1);
        assertNotEquals(field,field2);
    }
    
    @Test
    public void CompareToTest() {
        SudokuField field = new SudokuField();
        field.setFieldValue(2);
        SudokuField field1 = new SudokuField();
        field1.setFieldValue(2);
        assertEquals(field.compareTo(field1),0 );
        field.setFieldValue(3);
        assertEquals(field.compareTo(field1),1);
        field.setFieldValue(1);
        assertEquals(field.compareTo(field1),-1);
        
    }
    
    @Test
    public void testNullPointerException1() {
    SudokuField field = new SudokuField();
    field.setFieldValue(3);
    assertThrows(CompareToNullException.class,
            ()->{
                field.compareTo(null);
            });
    }
}
