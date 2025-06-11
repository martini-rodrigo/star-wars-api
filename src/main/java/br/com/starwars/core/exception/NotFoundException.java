package br.com.starwars.core.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object... param) {
        this(String.format(message.replace("{0}", "%s"), param));
    }
}
