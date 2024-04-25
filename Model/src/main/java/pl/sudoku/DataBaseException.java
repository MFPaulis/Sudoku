
package pl.sudoku;


public class DataBaseException extends ModelException {

    public static final String MESSAGE_KEY = "dataBaseException";
    
    public DataBaseException(String message) {
        super(message);
    }
    
    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
