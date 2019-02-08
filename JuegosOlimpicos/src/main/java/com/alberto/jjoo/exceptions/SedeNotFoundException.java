/**
 * 
 */
package com.alberto.jjoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Borak
 *
 * Excepción para controlar el NOT FOUND
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SedeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6764482073894953798L;

	/**
	 * 
	 */
	public SedeNotFoundException(String msg) {
		super(msg);
	}

}
