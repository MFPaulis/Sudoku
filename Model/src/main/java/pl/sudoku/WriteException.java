
package pl.sudoku;

public class WriteException extends DaoException {

    public static final String MESSAGE_KEY = "writeException";
    
    public WriteException(String message) {
        super(message);
    }
    
    public WriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
