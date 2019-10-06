package Persistence;

public class WrongFileException extends Exception {
    public WrongFileException(String wrong_input) {
        super(wrong_input);
    }
}
