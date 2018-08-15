package com.nelioalves.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 4384920945038295451L;

    public DataIntegrityException(String msg) {
	super(msg);
    }
    
    public DataIntegrityException(String msg, Throwable cause) {
	super(msg, cause);
    }
}
