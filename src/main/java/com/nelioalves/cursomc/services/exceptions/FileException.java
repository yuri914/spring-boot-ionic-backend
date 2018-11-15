package com.nelioalves.cursomc.services.exceptions;

public class FileException extends RuntimeException {

    private static final long serialVersionUID = 4384920945038295451L;

    public FileException(String msg) {
	super(msg);
    }
    
    public FileException(String msg, Throwable cause) {
	super(msg, cause);
    }
}
