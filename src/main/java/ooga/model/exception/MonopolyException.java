package ooga.model.exception;

public class MonopolyException extends RuntimeException {
  public MonopolyException() {
    super();
  }
  public MonopolyException(String message) {
    super(message);
  }

  /**
   * Create an exception based on an issue in our code.
   *
   * @author Robert Duvall
   */
  public MonopolyException(String message, Object... args) {
    super(String.format(message, args));
  }

  /**
   * Create an exception based on a caught exception with a different message.
   *
   * @author Robert Duvall
   */
  public MonopolyException(Throwable cause, String message, Object... args) {
    super(String.format(message, args), cause);
  }
}

