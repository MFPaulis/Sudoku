
package pl.comp.view;

public class BindingException extends ApplicationException {

    public static final String MESSAGE_KEY = "bindingException";
    
    public BindingException(String message) {
        super(message);
    }
    
    public BindingException(String message, Throwable cause) {
        super(message, cause);
    }
}
