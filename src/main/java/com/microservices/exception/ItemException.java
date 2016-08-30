package com.microservices.exception;

public class ItemException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ItemException(String message){
		super(message);
	}
	public ItemException(Exception exception){
		super(exception);
	}
	public ItemException(String message,Exception exception){
		super(message,exception);
	}
	
}
