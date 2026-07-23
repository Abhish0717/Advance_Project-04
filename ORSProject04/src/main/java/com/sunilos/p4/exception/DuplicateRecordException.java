package com.sunilos.p4.exception;

/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * 
 * @author Abhishish Bhawsar
 * @version 1.0
 * @Copyright (c) Rays Technologies
 * 
 */

public class DuplicateRecordException extends RuntimeException {

	/**
	 * @param msg error message
	 */
	public DuplicateRecordException(String msg) {
		super(msg);
	}

}
