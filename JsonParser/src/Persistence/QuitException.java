package Persistence;

import java.io.IOException;

public class QuitException extends Exception {
    public QuitException(String wrong_input) {
        super(wrong_input);
    }

}
