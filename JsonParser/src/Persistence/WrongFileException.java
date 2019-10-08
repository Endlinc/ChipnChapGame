package Persistence;

import java.io.IOException;

public class WrongFileException extends IOException {
    public WrongFileException(String wrong_input) {
        super(wrong_input);
    }
}
