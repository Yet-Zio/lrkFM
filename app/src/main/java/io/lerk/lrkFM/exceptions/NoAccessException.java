package io.lerk.lrkFM.exceptions;

import java.io.Serializable;

/**
 * {@link NoAccessException}.
 */
public class NoAccessException extends Exception implements Serializable {
    static final long serialVersionUID = 10L;

    public NoAccessException(String message) {
        super(message);
    }
}
