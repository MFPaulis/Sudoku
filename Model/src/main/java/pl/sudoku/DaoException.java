
package pl.sudoku;

public class DaoException extends ModelException {

    public static final String MESSAGE_KEY = "daoException";
    
    public DaoException(String message) {
        super(message);
    }
    
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
