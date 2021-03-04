package com.epsih.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DatabaseConstraints extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DatabaseConstraints(String message) {
		super(message);
	}

}
