package racingcar.error;

public class InvalidCarNameException extends IllegalArgumentException {
    public InvalidCarNameException(String message) {
        super(message);
    }
}
