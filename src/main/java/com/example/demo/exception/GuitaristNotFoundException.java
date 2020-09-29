package com.example.demo.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GuitaristNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1l;
}
