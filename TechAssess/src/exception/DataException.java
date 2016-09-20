package exception;

/**
 * <p>
 * Class which creates an custom exception of type data exception by accepting
 * the request and pass that reqeust to the constructor of super class called
 * Exception.
 * </p>
 *
 * @author TechAssess
 * @created 2016-08-27
 */
@SuppressWarnings("serial")
public class DataException extends Exception {

    public DataException(String message) {
        super(message);
    }
}
