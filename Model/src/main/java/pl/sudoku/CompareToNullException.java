
package pl.sudoku;


public class CompareToNullException extends ModelException {

    public static final String MESSAGE_KEY = "compareToNullException";
    
    public CompareToNullException(String message) {
        super(message);
    }
    
    public CompareToNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
