package scheper.mateus.exception;

import java.io.Serial;
import java.util.List;

public class BusinessException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(List<String> messages) {
        super(String.join("\n", messages));
    }

}