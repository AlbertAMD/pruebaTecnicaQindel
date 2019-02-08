/**
 * 
 */
package com.alberto.jjoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Borak
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SedeBadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -778994325537919995L;

	/**
	 * 
	 */
	public SedeBadRequestException(String msg) {
		super(msg);
	}

}
