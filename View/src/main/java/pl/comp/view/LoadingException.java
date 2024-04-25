
package pl.comp.view;

public class LoadingException extends ApplicationException {

    public static final String MESSAGE_KEY = "loadingException";
    
     public LoadingException(String message) {
        super(message);
    }
    
    public LoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
