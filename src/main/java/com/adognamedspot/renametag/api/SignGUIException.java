package com.adognamedspot.renametag.api;

/**
 * An exception thrown when an error occurs while using this api.
 */
public class SignGUIException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * {@inheritDoc}
     */
    public SignGUIException() {
    }

    /**
     * {@inheritDoc}
     */
    public SignGUIException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public SignGUIException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public SignGUIException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public SignGUIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}